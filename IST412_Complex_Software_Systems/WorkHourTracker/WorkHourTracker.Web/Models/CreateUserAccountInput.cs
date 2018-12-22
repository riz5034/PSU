using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using WorkHourTracker.Model.Constants;
namespace WorkHourTracker.Web.Models
{
    public class CreateUserAccountInput
    {
        [Required]
        [MinLength(5)]
        public string UserName { get; set; }

        [Required]
        [MinLength(5)]
        public string UserPassword { get; set; }

        
        [Required]
        [EnumDataType(typeof(Enums.RoleType))]
        public string UserRole { get; set; }

        [Required]
        [MinLength(1)]
        public string FirstName { get; set; }

        [Required]
        [MinLength(1)]
        public string LastName { get; set; }

    }
}
