using System;
using Xunit;
using WorkHourTracker.Data.Repository;
using WorkHourTracker.Model.Entities;
using System.Threading.Tasks;

namespace WorkHourTracker.Data.Test
{
    public class UserAccountRepositoryTest
    {
        private readonly UserAccountRepository _UserAccountRepo;

        public UserAccountRepositoryTest()
        {
            _UserAccountRepo = new UserAccountRepository();
        }

        [Fact]
        public async Task UserLogin_Success_Has_Data()
        {
            //Arrange
            var userLoginInput = new UserLoginDatabaseInput() { UserName = "TestUser", Password = "Test123" };
            var expectedGuid = Guid.Parse("6129FF13-1B5F-4281-AF45-47D32FA42176");
            //Act
            var result = await _UserAccountRepo.UserLogin(userLoginInput);

            //Assert
            Assert.NotNull(result);
        }

        //[Fact]
        //public async Task CreateAccount_Success()
        //{
        //    //Arrange
        //    var input = new CreateUserAccountDatabaseInput("JSmith", "123", "Employee", "John", "Smith");

        //    var userLoginInput = new UserLoginDatabaseInput() { UserName = "JSmith", Password = "123" };

        //    //Assert
        //    //Wait for the task to complete since we want the record to be created before the next step
        //    _UserAccountRepo.CreateUserAccount(input).Wait();

        //    var result = await _UserAccountRepo.UserLogin(userLoginInput);

        //    //Act
        //    Assert.NotNull(result);
        //    Assert.Equal(result.UserName, input.UserName);
        //    Assert.Equal(result.UserPassword, input.UserPassword);
        //    Assert.Equal(result.UserGuid.ToString(), input.UserGuid);
        //    Assert.Equal(result.EmployeeGuid.ToString(), input.EmployeeGuid);
        //}
        
        ////TODO: Make this Fact Unit test expect an Exception to be thrown
        //[Fact]
        //public async Task CreateUserAccount_ThrowsException_UserNameExists()
        //{
        //    //Arrange
        //    var input = new CreateUserAccountDatabaseInput("JSmith", "123123123", "Manager", "Jay", "Smyth");

        //    //Act
        //   await _UserAccountRepo.CreateUserAccount(input);

        //    //Assert
        //}
    }
}
