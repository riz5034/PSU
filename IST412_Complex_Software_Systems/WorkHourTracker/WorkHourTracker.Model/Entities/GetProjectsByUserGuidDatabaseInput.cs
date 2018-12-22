using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
   public class GetProjectsByUserGuidDatabaseInput
    {
        public string UserGuid { get; set; }

        public GetProjectsByUserGuidDatabaseInput(string userGuid)
        {
            UserGuid = userGuid;
        }
    }
}
