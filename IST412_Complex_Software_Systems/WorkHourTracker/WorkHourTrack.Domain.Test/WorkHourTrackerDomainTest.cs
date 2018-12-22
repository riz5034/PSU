using System;
using Xunit;
using WorkHourTracker.Domain.Interfaces;
using WorkHourTracker.Domain.Domains;
using WorkHourTracker.Model.Entities;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace WorkHourTrack.Domain.Test
{
    public class WorkHourTrackerDomainTest
    {
        private readonly IUserAccount _IUserAccount;

        public WorkHourTrackerDomainTest()
        {
            _IUserAccount = new UserAccountDomain();
        }

        //[Fact]
        //public async Task UserLogin_Success_HasData()
        //{
            
        //}

    }
}
