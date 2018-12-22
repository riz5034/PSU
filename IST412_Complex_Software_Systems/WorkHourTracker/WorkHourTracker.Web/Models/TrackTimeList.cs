using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Web.Models
{
    public class TrackTimeList
    {
        public List<GetTrackTimeDatabaseOutput> UserTrackTimeList { get; set; }
        public bool IsCurrentRecord { get; set; }
    }
}
