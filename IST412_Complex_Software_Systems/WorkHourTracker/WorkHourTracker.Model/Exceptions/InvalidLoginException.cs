using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Exceptions
{
  public  class InvalidLoginException : Exception
    {
        public InvalidLoginException()
        {

        }

        public InvalidLoginException(string message) : base(message)
        {

        }

        public InvalidLoginException(string message, Exception inner) : base(message, inner)
        {

        }
    }
}
