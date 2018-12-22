using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Data.Interfaces;
using WorkHourTracker.Data.Repository;
using WorkHourTracker.Domain.Interfaces;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Domain.Domains
{
   public class TrackTimeDomain : ITrackTimeDomain
    {

        private readonly ITrackTimeRepository _ITrackTimeRepo;

        public TrackTimeDomain()
        {
            _ITrackTimeRepo = new TrackTimeRepository();
        }

        /// <summary>
        /// Inserts a new record in the database. If a record is already there for the date range then update it
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task InsertTrackTime(TrackTimeDatabaseInput input) 
            => await _ITrackTimeRepo.InsertTrackTime(input);

        /// <summary>
        /// Get the TrackTime Details
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<List<GetTrackTimeDatabaseOutput>> GetTrackTime(GetTrackTimeDatabaseInput input)
        {
            return await _ITrackTimeRepo.GetTrackTime(input);
        }

        /// <summary>
        /// Gets the TrackTime DateRanges for UI display
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<List<GetTrackTimeDateRangeDatabaseOutput>> GetTrackTimeDateRanges(GetTrackTimeDateRangeDatabaseInput input)
        {
            return await _ITrackTimeRepo.GetTrakcTimeDateRanges(input);
        }

        /// <summary>
        /// Creates a new TrackTime record for new week
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task CreateNewTrackTimeRecord(CreateNewTrackTimeRecordDatabaseInput input)
        {
            await _ITrackTimeRepo.CreateNewTrackTimeRecord(input);
        }

        /// <summary>
        /// Gets the projects the user is assigne
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<List<GetProjectsByUserGuidDatabaseOutput>> GetProjectsByUserGuid(GetProjectsByUserGuidDatabaseInput input)
        {
            return await _ITrackTimeRepo.GetProjectsByUserGuid(input);
        }

        public DateTime FirstDayOfWeek(DateTime currentDate)
        {
            DayOfWeek dayOfWeek = CultureInfo.CurrentCulture.DateTimeFormat.FirstDayOfWeek;

            int dayOffSet = dayOfWeek - currentDate.DayOfWeek;

            DateTime startOfWeek = currentDate.AddDays(dayOffSet);

            return startOfWeek;
        }

        public DateTime LastDayOfWeek(DateTime currentDate)
        {
            DateTime lastDayOfWeek = FirstDayOfWeek(currentDate).AddDays(6);

            return lastDayOfWeek;
        }

        public async Task<bool> IsTrackTimeRecordCreatedForProject(CreateNewTrackTimeRecordDatabaseInput input)
        {
            return await _ITrackTimeRepo.IsTrackTimeRecordCreatedForProject(input);
        }

        /// <summary>
        /// This method will check to see if the record the use is clicking on is from
        /// a previous week or if it is the current week's record
        /// </summary>
        /// <param name="startDate"></param>
        /// <param name="endDate"></param>
        /// <returns></returns>
        public bool IsCurrentRecord(string startDate, string endDate)
        {
            bool isCurrentRecord = false;

            //get today's date
            var todaysDate = DateTime.Now;
            var shortDateToday = todaysDate.ToShortDateString();
            var today = Convert.ToDateTime(shortDateToday);

            //Convert startDate and endDate back to DateTime
            var start = Convert.ToDateTime(startDate);
            var end = Convert.ToDateTime(endDate);

            //is today's date within the supplied week's range?
            isCurrentRecord = (today >= start) && (today <= end);

            return isCurrentRecord;

        }
    }
}
