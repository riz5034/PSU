using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace WorkHourTracker.Web.Models
{
    public class AssignProjectInput
    {
        [Required]
        [MaxLength(50)]
        public string AssignedUserName { get; set; }

        [Required]
        [MaxLength(50)]
        public string AssignedProjectName { get; set; }

        [Required]
        [Range(0,8)]
        public int Capacity { get; set; }

    }
}
