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
using Microsoft.AspNetCore.Authorization;

namespace WorkHourTracker.Web.Controllers
{
    public class ProjectManagerController : Controller
    {
        private readonly IProjectManagerDomain _IProjectManagerDomain;

        public ProjectManagerController()
        {
            _IProjectManagerDomain = new ProjectManagerDomain();
        }

        public IActionResult Index()
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }
            return View();
        }

        /// <summary>
        /// This method will return the View (or page) where the Project Manager
        /// is able to create a new project
        /// </summary>
        /// <returns></returns>
        public IActionResult CreateProject()
        {
            if (TempData.Peek("userName") == null) 
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
               return UserNotAllowedAccess(isUserLoggedIn: true);
            }
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken] //this is here to prevent XSS (cross site scripting attacks)
        public async Task<IActionResult> SaveNewProject(CreateProjectInput newProjectInput)
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }
            //if the ModelState is invalid return the user to the CreateProject page and show them the validation errors
            if (!ModelState.IsValid)
            {
                List<string> errors = ModelState.Values.SelectMany(p => p.Errors.Select(x => x.ErrorMessage)).ToList();
                TempData.Add("CreateProjectInvalid", errors);
                return RedirectTo("ProjectManager", "CreateProject");
            }
          

            var resultList = new WorkHourTrackerListResult() { Errors = new List<string>(), WorkHourTrackList = new List<dynamic>() };

            //the request is valid. Now, transform it into CreateProjectDatabaseInput
            var databaseInput = new CreateProjectDatabaseInput(newProjectInput.ProjectName,
                                                               newProjectInput.ProjectCodeName,
                                                               TempData.Peek("userName").ToString());


            try
            {
                //send the request to the Domain layer
                await _IProjectManagerDomain.CreateNewProject(databaseInput);
                resultList.Errors.Add($"Project: {databaseInput.ProjectName} Project CodeName: {databaseInput.ProjectCodeName} has been created. Please assign the project to indivduals to being working on it.");
                TempData.Add("CreateProjectSuccess", resultList.Errors);
            }
            catch (Exception)
            {
                resultList.Errors.Add("The ProjectName or ProjectCodeName is already in use in the system. Please use another one.");
                TempData.Add("CreateProjectError", resultList.Errors);
                return RedirectTo("ProjectManager", "CreateProject");
            }


            return RedirectTo("ProjectManager", "CreateProject");

        }



        /// <summary>
        /// This method will return the View where the project manager
        /// can assign a project to an employee
        /// </summary>
        /// <returns></returns>
        public IActionResult AssignProject()
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }
            return View();
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
        /// This method will return the View where there project manager
        /// can save a project assignment
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<IActionResult> SaveProjectAssignment(WorkHourTracker.Web.Models.AssignProjectInput input)
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }

            //if the ModelState is invalid return the user to the AssignProject page and show them the validation errors
            if (!ModelState.IsValid)
            {
                List<string> errors = ModelState.Values.SelectMany(p => p.Errors.Select(x => x.ErrorMessage)).ToList();
                TempData.Add("AssignProjectInvalid", errors);
                return RedirectTo("ProjectManager", "AssignProject");
            }

            // Create a WorkHourTrackerListResult object and initializes its properties
            var resultList = new WorkHourTrackerListResult() { Errors = new List<string>(), WorkHourTrackList = new List<dynamic>() };

            // Since ViewModel input is valid, transform it into the DTO to transfer to other layers
            var databaseInput = new AssignProjectToEmployeeDatabaseInput(input.AssignedProjectName,
                                                                         input.AssignedUserName,
                                                                         input.Capacity,
                                                                         TempData.Peek("userName").ToString());

            try
            {

                await _IProjectManagerDomain.AssignProject(databaseInput);
                resultList.Errors.Add("The project has been successfully assigned!");
                TempData.Add("AssignedProjectSuccess", resultList.Errors);

            }
            catch (AssignAProjectException ex)
            {
                resultList.Errors.Add(ex.Message);
                TempData.Add("AssignedProjectError", resultList.Errors);
            }
            catch (Exception)
            {

                throw;
            }

            return RedirectTo("ProjectManager", "AssignProject");
        }

        public IActionResult EmployeeSearch()
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }

            return View();
        }

        [HttpPost]
        public async Task<IActionResult> GetEmployeeSearch(EmployeeSearchInput input)
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }

            //return the model state if the request is invalid
            if (!ModelState.IsValid)
            {
                List<string> errors = ModelState.Values.SelectMany(p => p.Errors.Select(x => x.ErrorMessage)).ToList();
                TempData.Add("EmployeeSearchInvalid", errors);
                return RedirectTo("ProjectManager", "EmployeeSearch");
            }

            var databaseInput = new EmployeeSearchDatabaseInput() { SearchedEmployeeUserName = input.SearchedEmployee };

            //define the output outside of the try catch block 
            EmployeeSearchOutput employeeSearchResult;

            try
            {
                //this is just a stub for now
                employeeSearchResult = await _IProjectManagerDomain.GetEmployeeSearch(databaseInput);
                employeeSearchResult.ChartData = employeeSearchResult.getCapacityForChart();

                if (employeeSearchResult.IsOverCapacity)
                {
                    TempData.Add("IsOverCapacity", "Warning: Thie employee is over capacity.");
                }
            }
            catch (Exception)
            {
                TempData.Add("NoEmployeeFound", $"The User Name you entered, {input.SearchedEmployee}, was either incorrect, does not exist in the system, or has no associated data in the system. Please verify the User Name is correct and and try again.");
                return RedirectTo("ProjectManager", "EmployeeSearch");
            }

            //return the View and pass in the Model
            return View(employeeSearchResult);
        }
       
        public IActionResult ProjectSearch()
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }

            return View();
        }

        /// <summary>
        /// Bring back Project Level information by the search project
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<IActionResult> DisplayProjectSearch(ProjectSearchInput input)
        {
            if (TempData.Peek("userName") == null)
            {
                return UserNotAllowedAccess(isUserLoggedIn: false);
            }

            if (TempData.Peek("userRole").ToString() == "Employee")
            {
                return UserNotAllowedAccess(isUserLoggedIn: true);
            }

            ProjectSearchDatabaseOutput result;

            var databaseInput = new ProjectSearchDatabaseInput { SearchedProject = input.SearchedProject };

            try
            {
                result = await _IProjectManagerDomain.ProjectSearch(databaseInput);
                result.ChartData = result.GetIndividualHoursForChart();
            }
            catch (Exception ex)
            {
                TempData.Add("ProjectSearchError", $"The project you searched for, {input.SearchedProject}, return no results. Please verify you have entered the correct Project Name or Project Code Name and try again.");
                return RedirectTo("ProjectManager", "ProjectSearch");
            }

            return View(result);
        }

        /// <summary>
        /// This method is used to route the user back to their current View
        /// if they are not logged in or not allowed access to a method
        /// </summary>
        /// <returns></returns>
        public IActionResult UserNotAllowedAccess(bool isUserLoggedIn)
        {
            if (!isUserLoggedIn)
            {
              TempData.Add("UserNotLoggedIn", "Please log into the application.");
              return RedirectTo("Home", "UserLogin");
            }

            TempData.Add("AccessDenied", "Sorry, you do not have access to that page.");
            return RedirectTo("Home", "Index");
               
        }

    }
}