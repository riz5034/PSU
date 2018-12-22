using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Data.Interfaces;
using WorkHourTracker.Data.Repository;
using WorkHourTracker.Domain.Interfaces;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Domain.Domains
{
   public class UserAccountDomain : IUserAccount
    {
        private readonly IUserAccountRepository _IUserAccountRepository;

        public UserAccountDomain()
        {
            _IUserAccountRepository = new UserAccountRepository();
        }

        /// <summary>
        /// Pass the user's credentials to the database to get the ApplicationUsers object
        /// </summary>
        /// <param name="input"></param>
        /// <returns></returns>
        public async Task<ApplicationUsers> UserLogin(UserLoginDatabaseInput input)
        {
            return await _IUserAccountRepository.UserLogin(input);
        }

        public async Task CreateUserAccount(CreateUserAccountDatabaseInput input)
        {
             await _IUserAccountRepository.CreateUserAccount(input);
        }
    }
}
