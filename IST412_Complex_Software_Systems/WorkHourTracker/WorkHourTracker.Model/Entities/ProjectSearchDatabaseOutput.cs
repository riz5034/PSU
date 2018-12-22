using System;
using System.Collections.Generic;
using System.Text;
using WorkHourTracker.Model.Entities;
namespace WorkHourTracker.Model.Entities
{
   public class ProjectSearchDatabaseOutput
    {
        public string ProjectName { get; set; }
        public string ProjectCodeName { get; set; }
        public string CreateUser { get; set; }
        public string CreateDate { get; set; }
        public string CsvIndividualsOnProject { get; set; }
        public string GrandTotalHoursWorkedOnProject { get; set; }
        public string CsvIndividualsHoursOnProjectForChart { get; set; }

        //Convert the DateTime from the database to Short Date Format
        public string ShortDate => Convert.ToDateTime(CreateDate).ToShortDateString();

        public List<IndividualEmployeeHoursOnProject> ChartData { get; set; }

        public List<IndividualEmployeeHoursOnProject> GetIndividualHoursForChart()
        {
            var splitBySemiColon = CsvIndividualsHoursOnProjectForChart.Split(';');

            List<IndividualEmployeeHoursOnProject> chartData = new List<IndividualEmployeeHoursOnProject>();

            foreach (var employee in splitBySemiColon)
            {
                var splitByComma = employee.Split(',');

                var individualHours = new IndividualEmployeeHoursOnProject
                {
                    EmployeeeName = splitByComma[0],
                    Hours = int.Parse(splitByComma[1])
                };

                chartData.Add(individualHours);
            }

            return chartData;
        }
    }
}
