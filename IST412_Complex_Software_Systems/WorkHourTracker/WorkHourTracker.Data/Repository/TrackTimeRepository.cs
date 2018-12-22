using System;
using System.Collections.Generic;
using System.Text;
using WorkHourTracker.Data.Interfaces;
using WorkHourTracker.Model.Entities;
using WorkHourTracker.Model.Exceptions;
using Dapper;
using System.Data;
using System.Data.SqlClient;
using System.Threading.Tasks;
using System.Linq;

namespace WorkHourTracker.Data.Repository
{
   public class TrackTimeRepository : ITrackTimeRepository
    {
        private readonly string _connectionString;
        
        public TrackTimeRepository()
        {
             _connectionString = "";
        }

        /// <summary>
        /// Save user TrackTime data to the database
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task InsertTrackTime(TrackTimeDatabaseInput input)
        {
            var p = new DynamicParameters();
            p.Add("@p_UserGuid", input.UserGuid);
            p.Add("@p_ProjectName", input.ProjectName);
            p.Add("@p_Comments", input.Comments);
            p.Add("@p_StartDate", input.StartDate);
            p.Add("@p_EndDate", input.EndDate);
            p.Add("@p_Sun", input.HourSun);
            p.Add("@p_Mon", input.HourMon);
            p.Add("@p_Tues", input.HourTues);
            p.Add("@p_Wed", input.HourWed);
            p.Add("@p_Thurs", input.HourThurs);
            p.Add("@p_Fri", input.HourFri);
            p.Add("@p_Sat", input.HourSat);

            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
                await connection.ExecuteAsync("sp_InsertTrackTime", p, commandType: CommandType.StoredProcedure);
            }
        }

        /// <summary>
        /// Given a userGuid return TrackTime data
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<List<GetTrackTimeDatabaseOutput>> GetTrackTime(GetTrackTimeDatabaseInput input)
        {
            var p = new DynamicParameters();

            p.Add("@p_UserGuid", input.UserGuid);
            p.Add("@p_StartDate", input.StartDate);
            p.Add("@p_EndDate", input.EndDate);

            IEnumerable<GetTrackTimeDatabaseOutput> trackTime;

            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
                trackTime =  await connection.QueryAsync<GetTrackTimeDatabaseOutput>("sp_GetTrackTime", p, commandType: CommandType.StoredProcedure);
            }

            var trackTimeList = trackTime.ToList();

            //Incase the comment is null make it a string
            trackTimeList.ForEach(x => x.Comments = x.Comments ?? "");

            return trackTimeList;
        }

        /// <summary>
        /// Get the TrackTime data ranges that will be displayed on the UI
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<List<GetTrackTimeDateRangeDatabaseOutput>> GetTrakcTimeDateRanges(GetTrackTimeDateRangeDatabaseInput input)
        {
            var p = new DynamicParameters();
            p.Add("@p_UserGuid", input.UserGuid);

            IEnumerable<GetTrackTimeDateRangeDatabaseOutput> dateRanges;

            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
                dateRanges = await connection.QueryAsync<GetTrackTimeDateRangeDatabaseOutput>("sp_GetTrackTimeDateRange", p, commandType: CommandType.StoredProcedure);
            }

           return dateRanges.OrderByDescending(x => x.StartDate).ToList();
        }

        /// <summary>
        /// Get the projects a user has been assigned
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<List<GetProjectsByUserGuidDatabaseOutput>> GetProjectsByUserGuid(GetProjectsByUserGuidDatabaseInput input)
        {
            var p = new DynamicParameters();
            p.Add("@p_UserGuid", input.UserGuid);

            IEnumerable<GetProjectsByUserGuidDatabaseOutput> projectNames;
            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
                //if the IEnumerable has a count of 0 throw an error since the user has not been assigned a project
                projectNames = await connection.QueryAsync<GetProjectsByUserGuidDatabaseOutput>("sp_GetProjectsByUserGuid", p, commandType: CommandType.StoredProcedure);
            }

            return projectNames.ToList();
        }

        /// <summary>
        /// Create a new TrackTime record
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task CreateNewTrackTimeRecord(CreateNewTrackTimeRecordDatabaseInput input)
        {
            var p = new DynamicParameters();

            p.Add("@p_UserGuid", input.UserGuid);
            p.Add("@p_ProjectName", input.ProjectName);
            p.Add("@p_StartDate", input.StartDate);
            p.Add("@p_EndDate", input.EndDate);

            var checkRecordExists = new DynamicParameters();

            checkRecordExists.Add("@p_UserGuid", input.UserGuid);
            checkRecordExists.Add("@p_StartDate", input.StartDate);
            checkRecordExists.Add("@p_EndDate", input.EndDate);

            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
                await connection.ExecuteAsync("sp_CreateNewTrackTimeRecord", p, commandType: CommandType.StoredProcedure);
            }
        }

        public async Task<bool> IsTrackTimeRecordCreatedForProject(CreateNewTrackTimeRecordDatabaseInput input)
        {
            var p = new DynamicParameters();

            p.Add("@p_UserGuid", input.UserGuid);
            p.Add("@p_ProjectName", input.ProjectName);
            p.Add("@p_StartDate", input.StartDate);
            p.Add("@p_EndDate", input.EndDate);

            bool isCreated = false;
            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
                isCreated = await connection.QueryFirstAsync<bool>("sp_IsProjectTrackTimeCreated", p, commandType: CommandType.StoredProcedure);
            }

            return isCreated;
        }
    }
}
