using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
   public class TrackTimeDatabaseInput
    {
        //class properties
        public string UserGuid { get; set; }
        public string ProjectName { get; set; }
        public int HourSun { get; set; }
        public int HourMon { get; set; }
        public int HourTues { get; set; }
        public int HourWed { get; set; }
        public int HourThurs { get; set; }
        public int HourFri { get; set; }
        public int HourSat { get; set; }
        public string Comments { get; set; }
        public string StartDate { get; set; }
        public string EndDate { get; set; }

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="userGuid"></param>
        /// <param name="projectName"></param>
        /// <param name="hourSun"></param>
        /// <param name="hourMon"></param>
        /// <param name="hourTues"></param>
        /// <param name="hourWed"></param>
        /// <param name="hourThurs"></param>
        /// <param name="hourFri"></param>
        /// <param name="hourSat"></param>
        /// <param name="startDate"></param>
        /// <param name="endDate"></param>
        public TrackTimeDatabaseInput(string userGuid, string projectName, int hourSun,
                                      int hourMon, int hourTues, int hourWed, int hourThurs,
                                      int hourFri, int hourSat, string comments, 
                                      string startDate, string endDate)
        {
            UserGuid = userGuid;
            ProjectName = projectName;

            HourSun = hourSun;
            HourMon = hourMon;
            HourTues = hourTues;
            HourWed = hourWed;
            HourThurs = hourThurs;
            HourFri = hourFri;
            HourSat = hourSat;

            //If the user did not enter a comment make it an empty string
            Comments = comments ?? "";

            StartDate = startDate;
            EndDate = endDate;
        }
    }
}
