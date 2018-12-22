using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Domain.Interfaces
{
   public interface IProjectManagerDomain
    {
        Task CreateNewProject(CreateProjectDatabaseInput input);
        Task AssignProject(AssignProjectToEmployeeDatabaseInput input);
        Task<EmployeeSearchOutput> GetEmployeeSearch(EmployeeSearchDatabaseInput input);
        Task<ProjectSearchDatabaseOutput> ProjectSearch(ProjectSearchDatabaseInput input);
    }
}
