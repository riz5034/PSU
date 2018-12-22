using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
    public class GetTrackTimeDatabaseInput
    {
        public string UserGuid { get; set; }
        public string StartDate { get; set; }
        public string EndDate { get; set; }

        public GetTrackTimeDatabaseInput(string userGuid, string startDate, string endDate)
        {
            UserGuid = userGuid;
            StartDate = startDate;
            EndDate = endDate;
        }
    }
}
