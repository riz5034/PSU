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
   public class UserAccountRepository : IUserAccountRepository
    {
        //the connection string to the Azure Database.
        //It is readonly as we do not want it to change once set
      
        private readonly string _connectionString = "";

        public UserAccountRepository()
        {
            
        }

        /// <summary>
        /// Use Dapper to call the data base and bring back the user account information
        /// </summary>
        /// <param name="input"></param>
        /// <returns>ApplicationUser</returns>
        public async Task<ApplicationUsers> UserLogin(UserLoginDatabaseInput input)
        {
            //Set up the DyanmicParameters object for Dapper
            var p = new DynamicParameters();
            //Add the input params to the dynamic parameter
            p.Add("@p_UserName", input.UserName);
            p.Add("@p_UserPassword", input.Password);

            IEnumerable<ApplicationUsers> result;

            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
               result = await connection.QueryAsync<ApplicationUsers>("sp_UserLogin", p, commandType: CommandType.StoredProcedure);

                if (result.Count() == 0)
                {
                    throw new InvalidLoginException("The user name or password was invalid");
                }
            }

            ApplicationUsers applicationUser = result.FirstOrDefault();

            return applicationUser;
        }

        /// <summary>
        /// Use Dapper to execute the sproc to create a new user
        /// </summary>
        /// <param name="input"></param>
        /// <returns>Nothing</returns>
        public async Task CreateUserAccount(CreateUserAccountDatabaseInput input)
        {
            var p = new DynamicParameters();

            p.Add("@p_UserGuid", input.UserGuid);
            p.Add("@p_UserName", input.UserName);
            p.Add("@p_UserPassword", input.UserPassword);
            p.Add("@p_UserRole", input.UserRole);
            p.Add("@p_EmployeeGuid", input.EmployeeGuid);
            p.Add("@p_FirstName", input.FirstName);
            p.Add("@p_LastName", input.LastName);

            var pUserNameCheck = new DynamicParameters();
            pUserNameCheck.Add("@p_UserName", input.UserName);

            bool isUserNameAvailable;

            using (IDbConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();

                isUserNameAvailable = await connection.QueryFirstAsync<bool>("sp_IsUserNameAvailable", pUserNameCheck, commandType: CommandType.StoredProcedure);

                if (!isUserNameAvailable)
                {
                    throw new CreateUserNameException("This user name is already in use. Please, enter a different one.");
                }

                await connection.ExecuteAsync("sp_CreateUserAccount", p, commandType: CommandType.StoredProcedure);
            }
        }

        }
    }
