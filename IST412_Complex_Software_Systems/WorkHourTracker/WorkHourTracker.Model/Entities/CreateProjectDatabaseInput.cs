using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
  public class CreateProjectDatabaseInput
    {
        public string ProjectGuid { get; set; }
        public string ProjectName { get; set; }
        public string ProjectCodeName { get; set; }
        public DateTime CreateDate => DateTime.Now;
        public string CreateUser { get; set; }

        public CreateProjectDatabaseInput(string projectName, string projectCodeName, string userName)
        {
            ProjectGuid = Guid.NewGuid().ToString();
            ProjectName = projectName;
            ProjectCodeName = projectCodeName;
            CreateUser = userName;
        }
    }
}
