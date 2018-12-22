using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
   public class CreateNewTrackTimeRecordDatabaseInput
    {
        public string UserGuid { get; set; }
        public string ProjectName { get; set; }
        public string StartDate { get; set; }
        public string EndDate { get; set; }

        public CreateNewTrackTimeRecordDatabaseInput(string userGuid, string projectName, string startDate, string endDate)
        {
            UserGuid = userGuid;
            ProjectName = projectName;
            StartDate = startDate;
            EndDate = endDate;
        }
    }
}
