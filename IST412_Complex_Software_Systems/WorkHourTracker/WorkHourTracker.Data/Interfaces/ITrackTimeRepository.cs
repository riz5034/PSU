using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Data.Interfaces
{
   public interface ITrackTimeRepository
    {
        Task InsertTrackTime(TrackTimeDatabaseInput input);
        Task<List<GetTrackTimeDatabaseOutput>> GetTrackTime(GetTrackTimeDatabaseInput input);
        Task<List<GetTrackTimeDateRangeDatabaseOutput>> GetTrakcTimeDateRanges(GetTrackTimeDateRangeDatabaseInput input);
        Task<List<GetProjectsByUserGuidDatabaseOutput>> GetProjectsByUserGuid(GetProjectsByUserGuidDatabaseInput input);
        Task CreateNewTrackTimeRecord(CreateNewTrackTimeRecordDatabaseInput input);
        Task<bool> IsTrackTimeRecordCreatedForProject(CreateNewTrackTimeRecordDatabaseInput input);
    }
}
