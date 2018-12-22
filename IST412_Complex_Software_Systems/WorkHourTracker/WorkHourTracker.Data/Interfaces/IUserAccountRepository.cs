using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Model.Entities;
namespace WorkHourTracker.Data.Interfaces
{
    public interface IUserAccountRepository
    {
        Task<ApplicationUsers> UserLogin(UserLoginDatabaseInput input);
        Task CreateUserAccount(CreateUserAccountDatabaseInput input);
    }
}
