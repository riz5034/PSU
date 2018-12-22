using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Domain.Interfaces
{
   public interface ITrackTimeDomain
    {
        Task InsertTrackTime(TrackTimeDatabaseInput input);
        Task<List<GetTrackTimeDatabaseOutput>> GetTrackTime(GetTrackTimeDatabaseInput input);
        Task<List<GetTrackTimeDateRangeDatabaseOutput>> GetTrackTimeDateRanges(GetTrackTimeDateRangeDatabaseInput input);
        Task CreateNewTrackTimeRecord(CreateNewTrackTimeRecordDatabaseInput input);
        Task<List<GetProjectsByUserGuidDatabaseOutput>> GetProjectsByUserGuid(GetProjectsByUserGuidDatabaseInput input);
        DateTime LastDayOfWeek(DateTime currentDate);
        DateTime FirstDayOfWeek(DateTime currentDate);
        Task<bool> IsTrackTimeRecordCreatedForProject(CreateNewTrackTimeRecordDatabaseInput input);
        bool IsCurrentRecord(string startDate, string endDate);
    }
}
