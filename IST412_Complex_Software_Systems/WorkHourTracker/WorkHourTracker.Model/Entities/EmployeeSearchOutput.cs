using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Entities
{
    public class EmployeeSearchOutput
    {
        public string EmployeeFirstName { get; set; }
        public string EmployeeLastName { get; set; }
        public string EmployeeUserName { get; set; }
        public string CsvAssignedProjects { get; set; }
        public string CapacityPerProject { get; set; }
        public string CapacityPerProjectStringForCharts { get; set; }
        public string HoursWorkedPerProject { get; set; }

        public List<EmployeeCapacity> ChartData { get; set; }
        public bool IsOverCapacity { get; set; }


        public List<EmployeeCapacity> getCapacityForChart()
        {
            var splitBySemiColon = CapacityPerProjectStringForCharts.Split(';');

            List<EmployeeCapacity> chartData = new List<EmployeeCapacity>();

            foreach (var project in splitBySemiColon)
            {
                var splitByComma = project.Split(',');

                var capacity = new EmployeeCapacity
                {
                    ProjectName = splitByComma[0],
                    CapacityHours = int.Parse(splitByComma[1])
                };

                chartData.Add(capacity);
            }

            int totalCapacity = 0;

            chartData.ForEach(p => totalCapacity += p.CapacityHours);

            IsOverCapacity = true;

            if (totalCapacity < 8)
            {
                IsOverCapacity = false;
                int freeTime = 8 - totalCapacity;

                chartData.Add(new EmployeeCapacity { ProjectName = "Free", CapacityHours = freeTime });
            }

            return chartData;
        }
    }
}
