using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
    public class ApplicationUsers
    {
        public Guid UserGuid { get; set; }
        public string UserName { get; set; }
        public string UserPassword { get; set; }
        public string UserRole { get; set; }
        public Guid EmployeeGuid { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
    }
}
