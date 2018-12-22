using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
namespace WorkHourTracker.Web.Models
{

    public class CreateProjectInput
    {        
        [Required]
        [MaxLength(50)]
        public string ProjectName { get; set; }

        [Required]
        [MaxLength(20)]
        public string ProjectCodeName { get; set; }

    }
}
