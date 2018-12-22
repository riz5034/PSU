using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
    /// <summary>
    /// This class will be the object that holds the properties needed to assign a project to an employee
    /// </summary>
    public class AssignProjectToEmployeeDatabaseInput
    {
        public string AssignedProjectName { get; set; }
        public string AssignedUserName { get; set; }
        public int Capacity { get; set; }
        public string CreateUser { get; set; }

        public AssignProjectToEmployeeDatabaseInput(string projectName, string userName, int capacity, string createUser)
        {
            AssignedProjectName = projectName;
            AssignedUserName = userName;
            Capacity = capacity;
            CreateUser = createUser;
        }
    }
}
