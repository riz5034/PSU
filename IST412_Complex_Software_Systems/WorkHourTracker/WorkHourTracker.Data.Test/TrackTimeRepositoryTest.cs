using System;
using Xunit;
using WorkHourTracker.Data.Repository;
using WorkHourTracker.Model.Entities;
using System.Threading.Tasks;
using System.Collections.Generic;

namespace WorkHourTracker.Data.Test
{
    public class TrackTimeRepositoryTest
    {
        private readonly TrackTimeRepository _trackTimeRepo;

        public TrackTimeRepositoryTest()
        {
            _trackTimeRepo = new TrackTimeRepository();
        }

        /// <summary>
        /// Test to make sure the application can save TrackTime data successfully
        /// </summary>
        /// <returns></returns>
        [Fact]
        public async Task InsertTrackTime_Sucess()
        {
            //Arrange
            var input = new TrackTimeDatabaseInput("67d537cb-123d-424b-a6d1-2cc5b2920430",
                                                   "ProjectBeets",
                                                   5, 1, 2, 2, 0, 2, 3, "Started working on setting the project up.", 
                                                   "11/04/18", "11/10/18");
            //Act
            await _trackTimeRepo.InsertTrackTime(input);

            //Assert
        }

        [Fact]
        public async Task GetTrackTime_Success()
        {
            //Arrange
            var userGuid = new GetTrackTimeDatabaseInput("67d537cb-123d-424b-a6d1-2cc5b2920430", 
                                                         "11/11/18", "11/17/18");

            IEnumerable<GetTrackTimeDatabaseOutput> tracktTimeList;

            //Act
            tracktTimeList = await _trackTimeRepo.GetTrackTime(userGuid);

            //Assert
            Assert.NotNull(tracktTimeList);
        }

        [Fact]
        public async Task GetTrackTimeDateRange_Success()
        {
            //Arrange
            var input = new GetTrackTimeDateRangeDatabaseInput("67d537cb-123d-424b-a6d1-2cc5b2920430");

            var dateRangeList = new List<GetTrackTimeDateRangeDatabaseOutput>();

            //Act
            dateRangeList = await _trackTimeRepo.GetTrakcTimeDateRanges(input);

            //Assert
            Assert.NotNull(dateRangeList);
        }

        [Fact]
        public async Task GetProjectsByUserGuid()
        {
            //Arrange
            var input = new GetProjectsByUserGuidDatabaseInput("67d537cb-123d-424b-a6d1-2cc5b2920430");

            //Act
            var projectNames = await _trackTimeRepo.GetProjectsByUserGuid(input);

            //Assert
            Assert.NotNull(projectNames);
        }

        [Fact]
        public async Task CreateNewTrackTimeRecord()
        {
            //Arrange
            var createNewRecordInput = new CreateNewTrackTimeRecordDatabaseInput("ca0e7040-b42c-4cf3-9b4e-4a9a06129cec", "ProjectPaper", "11/11/18", "11/17/18");


            var getTrackTimeInput = new GetTrackTimeDatabaseInput("ca0e7040-b42c-4cf3-9b4e-4a9a06129cec", "11/11/18", "11/17/18");
            var trackTimeList = new List<GetTrackTimeDatabaseOutput>();

            //Act
            await _trackTimeRepo.CreateNewTrackTimeRecord(createNewRecordInput);

            trackTimeList = await _trackTimeRepo.GetTrackTime(getTrackTimeInput);

            //Assert
            Assert.NotNull(trackTimeList);
        }
    }
}
