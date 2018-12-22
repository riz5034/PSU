using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WorkHourTracker.Web.Models
{
    public class UserLoginInput
    {
        [Required]
        [MinLength(5)]
        public string UserName { get; set; }

        [Required]
        [MinLength(5)]
        public string Password { get; set; }
    }
}
