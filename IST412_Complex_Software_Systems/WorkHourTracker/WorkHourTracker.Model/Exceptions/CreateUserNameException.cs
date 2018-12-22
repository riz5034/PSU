using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Exceptions
{
   public class CreateUserNameException : Exception
    {
        public CreateUserNameException()
        {

        }

        public CreateUserNameException(string message) : base(message)
        {

        }

        public CreateUserNameException(string message, Exception inner) : base(message, inner)
        {

        }
    }
}
