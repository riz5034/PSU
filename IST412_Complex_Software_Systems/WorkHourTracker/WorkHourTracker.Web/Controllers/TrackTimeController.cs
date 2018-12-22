using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WorkHourTracker.Web.Models;
using WorkHourTracker.Web.Infrastructure;
using WorkHourTracker.Model.Entities;
using WorkHourTracker.Model.Exceptions;
using WorkHourTracker.Domain.Interfaces;
using WorkHourTracker.Domain.Domains;

namespace WorkHourTracker.Web.Controllers
{
    public class TrackTimeController : Controller
    {
        private readonly ITrackTimeDomain _ITrackTimeDomain;

        public TrackTimeController()
        {
            _ITrackTimeDomain = new TrackTimeDomain();
        }

        public async Task<IActionResult> DisplayTrackTimeDateRange()
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess();
            }

            //get the input object set up
            var dateRangeInput = new GetTrackTimeDateRangeDatabaseInput(TempData.Peek("userGuid").ToString());

            List<GetTrackTimeDateRangeDatabaseOutput> trackTimeDateRanges;
            try
            {
                //using the input object get the user's TrackTime date range(s)
                trackTimeDateRanges = await _ITrackTimeDomain.GetTrackTimeDateRanges(dateRangeInput);
            }
            catch (Exception)
            {

                throw;
            }

            return View(trackTimeDateRanges);
        }

        /// <summary>
        /// Create a new TrackTime record
        /// </summary>
        /// <returns></returns>
        public async Task<IActionResult> CreateNewTrackTimeRecord()
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess();
            }

            string startOfWeek;
            string lastOfWeek;

            //see if there was at least one track time record created
            bool anyUpdates = false;
            try
            {
                var getProjectNamesInput = new GetProjectsByUserGuidDatabaseInput(TempData.Peek("userGuid").ToString());

                //get the projects the user has been assigned
                var projectNamesList = await _ITrackTimeDomain.GetProjectsByUserGuid(getProjectNamesInput);

                //if the user has not been assigned a project yet throw this error
                if (projectNamesList.Count == 0)
                {
                    throw new TrackTimeRecordAlreadyExistsException("You do not have any projects assigned. Please contact your Project Manager or Manager.");
                }

                //Get today's date
                var currentDay = DateTime.Now;

                //using today's date get the start and end dates of the week
                startOfWeek = _ITrackTimeDomain.FirstDayOfWeek(currentDay).ToShortDateString();
                lastOfWeek = _ITrackTimeDomain.LastDayOfWeek(currentDay).ToShortDateString();

                //foreach assigned project, create a new TrackTime record 
                bool isRecordCreated = false;
                foreach (var project in projectNamesList)
                {
                    var createTrackTimeInput = new CreateNewTrackTimeRecordDatabaseInput(TempData.Peek("userGuid").ToString(),
                                                                                         project.ProjectName, startOfWeek,
                                                                                         lastOfWeek
                                                                                         );

                    //check to see if there already is a track time record for a proejct for the week
                    isRecordCreated = await _ITrackTimeDomain.IsTrackTimeRecordCreatedForProject(createTrackTimeInput);

                    //if no record exists then create one
                    if (!isRecordCreated)
                    {
                        await _ITrackTimeDomain.CreateNewTrackTimeRecord(createTrackTimeInput);
                        anyUpdates = true;
                    }

                    
                }

                if (!anyUpdates) { throw new TrackTimeRecordAlreadyExistsException($"A TrackTime entry has already been created for the week: {startOfWeek} - {lastOfWeek}"); }


            }
            catch (TrackTimeRecordAlreadyExistsException ex)
            {
                //The user clicked 'Create New Record' for a week in which a record is already created
                TempData.Add("CreateTrackTimeError", ex.Message);

                return RedirectTo("TrackTime", "DisplayTrackTimeDateRange");

            }
            catch (Exception ex)
            {
                TempData.Add("CreateTrackTimeError", ex.Message);

                return RedirectTo("TrackTime", "DisplayTrackTimeDateRange");
            }

            return RedirectToAction("DisplayTrackTimeDetails", "TrackTime", new { startDate = startOfWeek, endDate = lastOfWeek });
        }

        public async Task<IActionResult> DisplayTrackTimeDetails(string startDate, string endDate)
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess();
            }

            //get the track time details for the newly created records
            var getTrackTimeInput = new GetTrackTimeDatabaseInput(TempData.Peek("userGuid").ToString(), startDate, endDate);

            var trackTimeDetails = await _ITrackTimeDomain.GetTrackTime(getTrackTimeInput);

            //Set the UserTrackTimeList property of the ViewModel
            TrackTimeList trackTimeDetailsList = new TrackTimeList { UserTrackTimeList = trackTimeDetails };
            trackTimeDetailsList.IsCurrentRecord = true;
            if (!_ITrackTimeDomain.IsCurrentRecord(startDate, endDate))
            {
                TempData.Add("ReadonlyTrackTime", "This is record is for a previous week's Track Time.");
                trackTimeDetailsList.IsCurrentRecord = false;
                //return RedirectTo("TrackTime", "DisplayReadonlyTrackTime");
            }


            //return the View and pass in the user's track time details list
            return View(trackTimeDetailsList);
        }

        /// <summary>
        /// Save the user's track time data
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<IActionResult> SaveTrackTime(TrackTimeList input)
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess();
            }

            var workHourTrackerList = new WorkHourTrackerListResult() { Errors = new List<string>(), WorkHourTrackList = new List<dynamic>() };

            //get the start and end dates so we can display the track time details page when this operation is done
            string startOfWeek = input.UserTrackTimeList[0].StartDate;
            string lastOfWeek = input.UserTrackTimeList[0].EndDate;

            //if the ModelState is invalid return the user to the CreateProject page and show them the validation errors
            if (!ModelState.IsValid)
            {
                List<string> errors = ModelState.Values.SelectMany(p => p.Errors.Select(x => x.ErrorMessage)).ToList();
                TempData.Add("SaveTrackTimeError", errors);
                return RedirectToAction("DisplayTrackTimeDetails", "TrackTime", new { startDate = startOfWeek, endDate = lastOfWeek });
            }

            try
            {
                //foreach record in the input list, save the new track time data
                foreach (var project in input.UserTrackTimeList)
                {
                    var saveTrackTimeInput =
                        new TrackTimeDatabaseInput(TempData.Peek("userGuid").ToString(), project.ProjectName,
                                                   project.HourSun, project.HourMon, project.HourTues, project.HourWed,
                                                   project.HourThurs, project.HourFri, project.HourSat, project.Comments,
                                                   project.StartDate, project.EndDate);

                   await _ITrackTimeDomain.InsertTrackTime(saveTrackTimeInput);

                   
                }
            }
            catch (Exception)
            {

                throw;
            }
            workHourTrackerList.Errors.Add("TrackTime data has been saved!");
            TempData.Add("SaveTrackTime", workHourTrackerList.Errors);

            return RedirectToAction("DisplayTrackTimeDetails", "TrackTime", new { startDate = startOfWeek, endDate = lastOfWeek });
        }

        public IActionResult DisplayReadonlyTrackTime()
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess();
            }

            GetTrackTimeDatabaseOutput[] array = (GetTrackTimeDatabaseOutput[])TempData["ReadonlyTrackTime"];

            TrackTimeList trackTimeList = new TrackTimeList();

            for (int i = 0; i < array.Length; i++)
            {
                trackTimeList.UserTrackTimeList.Add(array[i]);
            }


            return View(trackTimeList);
        }

        /// <summary>
        /// Use this method to direct to another controller / action
        /// </summary>
        /// <param name="controllerName"></param>
        /// <param name="actionMethodName"></param>
        /// <returns></returns>
        public IActionResult RedirectTo(string controllerName, string actionMethodName)
        {
            return RedirectToRoute(new
            {
                controller = controllerName,
                action = actionMethodName
            });
        }

        /// <summary>
        /// This method is used to route the user back to their current View
        /// if they are not logged in or not allowed access to a method
        /// </summary>
        /// <returns></returns>
        public IActionResult UserNotAllowedAccess()
        {
            TempData.Add("UserNotLoggedIn", "Please log into the application.");
            return RedirectTo("Home", "UserLogin");
        }


    }
}