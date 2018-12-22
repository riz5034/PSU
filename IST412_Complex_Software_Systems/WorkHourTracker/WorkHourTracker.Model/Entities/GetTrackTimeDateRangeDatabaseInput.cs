using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
   public class GetTrackTimeDateRangeDatabaseInput
    {
        public string UserGuid { get; set; }

        public GetTrackTimeDateRangeDatabaseInput(string userGuid)
        {
            UserGuid = userGuid;
        }
    }
}
