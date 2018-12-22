using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
   public class TrackEmployeeTime
    {
        public string EmployeeName { get; set; }
        public string Project { get; set; }
        public double TimeLogged { get; set; }
        public DateTime DateRecordCreated => DateTime.Now;
    }
}
