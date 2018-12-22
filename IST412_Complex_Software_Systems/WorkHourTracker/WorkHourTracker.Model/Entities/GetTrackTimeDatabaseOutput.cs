using System;
using System.Collections.Generic;
using System.Text;
using System.ComponentModel.DataAnnotations;
namespace WorkHourTracker.Model.Entities
{
   public class GetTrackTimeDatabaseOutput
    {
        public string ProjectName { get; set; }

        [Required]
        [Range(0, 8)]
        public int HourSun { get; set; }
        [Required]
        [Range(0, 8)]
        public int HourMon { get; set; }
        [Required]
        [Range(0, 8)]
        public int HourTues { get; set; }
        [Required]
        [Range(0, 8)]
        public int HourWed { get; set; }
        [Required]
        [Range(0, 8)]
        public int HourThurs { get; set; }
        [Required]
        [Range(0, 8)]
        public int HourFri { get; set; }
        [Required]
        [Range(0, 8)]
        public int HourSat { get; set; }

        public string Comments { get; set; }

        public string StartDate { get; set; }
        public string EndDate { get; set; }
    }
}
