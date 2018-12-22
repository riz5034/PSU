using System;
using Xunit;
using WorkHourTracker.Data.Repository;
using WorkHourTracker.Model.Entities;
using System.Threading.Tasks;

namespace WorkHourTracker.Data.Test
{
  public class ProjectManagerRepositoryTest
    {
        private readonly ProjectManagerRepository _projectManagerRepository;

        public ProjectManagerRepositoryTest()
        {
            _projectManagerRepository = new ProjectManagerRepository();
        }

        //[Fact]
        //public async Task CreateNewProject_Success()
        //{
        //    //Arrange
        //    var input = new CreateProjectDatabaseInput("Project X", "X", "AVandelay");

        //    //Act
        //    await _projectManagerRepository.CreateNewProject(input);
        //    //Assert
        //}

        [Fact]
        public async Task GetEmployeeSearch()
        {
            //Arrange
            var input = new EmployeeSearchDatabaseInput() { SearchedEmployeeUserName = "DSchrute" };

            //Act
            var result = await _projectManagerRepository.GetEmployeeSearch(input);

            //Assert
            Assert.NotNull(result);
        }

        [Fact]
        public async Task ProjectSearch_ByProjectName()
        {
            //Arrange
            var input = new ProjectSearchDatabaseInput { SearchedProject = "ProjectPaper" };

            //Act
            var result = await _projectManagerRepository.ProjectSearch(input);

            //Assert
            Assert.NotNull(result);
            Assert.Equal("11/8/2018", result.ShortDate);
        }

        [Fact]
        public async Task ProjectSearch_ByProjectCodeName()
        {
            //Arrange
            var input = new ProjectSearchDatabaseInput { SearchedProject = "Paper" };

            //Act
            var result = await _projectManagerRepository.ProjectSearch(input);

            //Assert
            Assert.NotNull(result);
            Assert.Equal("11/8/2018", result.ShortDate);
        }
    }
}
