using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
   public class ProjectHistory
    {
        public List<TrackEmployeeTime> EmployeesOnProject { get; set; }
        public double TotalHoursLogged { get; set; }

    }
}
