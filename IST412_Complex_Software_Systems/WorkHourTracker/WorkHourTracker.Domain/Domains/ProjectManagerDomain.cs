using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using WorkHourTracker.Data.Interfaces;
using WorkHourTracker.Data.Repository;
using WorkHourTracker.Domain.Interfaces;
using WorkHourTracker.Model.Entities;

namespace WorkHourTracker.Domain.Domains
{
    public class ProjectManagerDomain : IProjectManagerDomain
    {
        private readonly IProjectManagerRepository _IProjectManagerRepository;

        public ProjectManagerDomain()
        {
            _IProjectManagerRepository = new ProjectManagerRepository();
        }

        public async Task CreateNewProject(CreateProjectDatabaseInput input)
        {
            await _IProjectManagerRepository.CreateNewProject(input);
        }

        public async Task AssignProject(AssignProjectToEmployeeDatabaseInput input)
        {
            await _IProjectManagerRepository.AssignProject(input);
        }

       public async Task<EmployeeSearchOutput> GetEmployeeSearch(EmployeeSearchDatabaseInput input)
        {
            return await _IProjectManagerRepository.GetEmployeeSearch(input);
        }

        public async Task<ProjectSearchDatabaseOutput> ProjectSearch(ProjectSearchDatabaseInput input)
        {
            return await _IProjectManagerRepository.ProjectSearch(input);
        }
    }
}
