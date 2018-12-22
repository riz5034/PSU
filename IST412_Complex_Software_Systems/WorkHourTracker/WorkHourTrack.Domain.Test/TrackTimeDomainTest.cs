using System;
using Xunit;
using WorkHourTracker.Domain.Interfaces;
using WorkHourTracker.Domain.Domains;
using WorkHourTracker.Model.Entities;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace WorkHourTracker.Domain.Test
{
    public class TrackTimeDomainTest
    {
        private readonly TrackTimeDomain _trackTimeDomain;

        public TrackTimeDomainTest()
        {
            _trackTimeDomain = new TrackTimeDomain();
        }

        [Fact]
        public void FirstDayOfWeek_Success()
        {
            //Arrange
            var currentDate = new DateTime(2018, 11, 17);

            var expectedDate = new DateTime(2018, 11, 11);
            //Act
            var firstOfWeek = _trackTimeDomain.FirstDayOfWeek(currentDate);

            //Assert
            Assert.Equal(expectedDate, firstOfWeek);

        }

        [Fact]
        public void LastDayOfWeek()
        {
            //Arrange
            var currentDate = new DateTime(2018, 11, 11);

            var expectedDate = new DateTime(2018, 11, 17);

            //Act
            var lastOfWeek = _trackTimeDomain.LastDayOfWeek(currentDate);

            //Assert
            Assert.Equal(expectedDate, lastOfWeek);
        }

        [Fact]
        public void IsCurrentRecord_False()
        {
            //Arrange
            string startDate = "11/11/2018";
            string endDate = "11/17/2018";

            //Act
            var boolResult = _trackTimeDomain.IsCurrentRecord(startDate, endDate);

            //Assert
            Assert.False(boolResult);
        }
    }
}
