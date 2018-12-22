using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WorkHourTracker.Web.Infrastructure
{
    public class WorkHourTrackerListResult
    {
        public List<dynamic> WorkHourTrackList { get; set; }
        public List<string> Errors { get; set; }
    }
}
