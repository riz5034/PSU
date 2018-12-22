using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Exceptions
{
   public class AssignAProjectException : Exception
    {
        public AssignAProjectException() : base()
        {

        }

        public AssignAProjectException(string message) : base(message)
        {

        }

        public AssignAProjectException(string message, Exception inner) : base(message, inner)
        {

        }
    }
}
