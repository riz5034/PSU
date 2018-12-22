using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Domain.Interfaces
{
   public interface IUserAccount
    {
        Task<ApplicationUsers> UserLogin(UserLoginDatabaseInput input);
        Task CreateUserAccount(CreateUserAccountDatabaseInput input);
    }

}
