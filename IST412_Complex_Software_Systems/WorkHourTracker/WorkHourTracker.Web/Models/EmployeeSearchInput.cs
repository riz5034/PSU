using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace WorkHourTracker.Web.Models
{
    public class EmployeeSearchInput
        {
        [Required]
    public string SearchedEmployee { get; set; }
        }
}