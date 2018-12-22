using System;
using System.Collections.Generic;
using System.Text;
using WorkHourTracker.Model.Constants;
namespace WorkHourTracker.Model.Entities
{
    /// <summary>
    /// This class will be the object that holds the properties needed to create a new user account
    /// </summary>
   public class CreateUserAccountDatabaseInput
    {
        public string UserGuid { get; set; }
        public string UserName { get; set; }
        public string UserPassword { get; set; }
        public string UserRole { get; set; }
        public string EmployeeGuid { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }


        public CreateUserAccountDatabaseInput(string userName, string userPassword, string userRole, string firstName, string lastName)
        {
            UserGuid = Guid.NewGuid().ToString();
            UserName = userName;
            UserPassword = userPassword;

            //convert the enum to the string representation
            Enums.RoleType role;
            Enum.TryParse(userRole, out role);
            UserRole = role.ToString();

            EmployeeGuid = Guid.NewGuid().ToString();
            FirstName = firstName;
            LastName = lastName;
        }
    }
}
