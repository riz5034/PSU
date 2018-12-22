using System;
using System.Collections.Generic;
using System.Text;

namespace WorkHourTracker.Model.Exceptions
{
   public class TrackTimeRecordAlreadyExistsException : Exception
    {
        public TrackTimeRecordAlreadyExistsException() : base()
        {

        }

        public TrackTimeRecordAlreadyExistsException(string message) : base(message)
        {

        }

        public TrackTimeRecordAlreadyExistsException(string message, Exception inner) : base(message, inner)
        {

        }
    }
}
