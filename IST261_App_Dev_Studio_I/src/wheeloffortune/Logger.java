/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Ricky Zhao
* ID: riz5034
*/
package wheeloffortune;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* SBI-06
* The Logger class is used to log messages to help with debugging
* Logger is a singleton so only one instance of Logger can exist
* Logger accepts a message to write to a file
* Source:http://brandonclapp.com/what-is-a-singleton-how-do-i-implement-it-and-when-do-i-use-it/
* @author Ricky Zhao <riz5034@psu.edu> 
*/
public class Logger {
    private static Logger logger = new Logger();
    
    public enum LogLevel {
        ALL,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }
    
    /* 
    * Private constructor that can only be called
    * from within the Logger class
    * @author Ricky Zhao <riz5034@psu.edu>
    */
    private Logger() {
        
    }
    
    /*
    * SBI-19
    * Returns reference to the single Logger instance
    * @author Ricky Zhao <riz5034@psu.edu>
    */
    public static Logger getLogger() {
        return logger;
    }
    
    /* 
    * SBI-06
    * Method to write a given log message to a file 
    * A log level is needed in the parameter to determine importance of messages
    * Logs are appended with the new line character to logs.txt
    * Logs begin with a date/time prefix
    * @author Ricky Zhao <riz5034@psu.edu>
    */
    public void log(LogLevel logLevel, String msg) throws IOException {
        FileWriter fileWriter = new FileWriter("logs.txt", true);
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
       
        // Fix Config.java to read from config.properties
        // Use String foo = Config.getSetting("foo");
        String config = Config.getSetting("logLevel");
        LogLevel configLevel = LogLevel.valueOf(config);
        boolean writeLog = false;
        
        // SBI-06
        // Determines if the log message falls under the log level
        // specified in the config file
        switch(configLevel) {
            case ALL:
                if(LogLevel.ALL == logLevel) {
                    writeLog = true;
                }
            case DEBUG:
                if(LogLevel.DEBUG == logLevel) {
                    writeLog = true;
                }
            case INFO:
                if(LogLevel.INFO == logLevel) {
                    writeLog = true;
                }
            case WARN:
                if(LogLevel.WARN == logLevel) {
                    writeLog = true;
                }
            case ERROR:
                if(LogLevel.ERROR == logLevel) {
                    writeLog = true;
                }
            case FATAL:
                if(LogLevel.FATAL == logLevel) {
                    writeLog = true;
                }
            default: break;
        }
        
        if(writeLog) {
            fileWriter.append(simpleDate.format(date) + " [" + logLevel.toString() + "] " + msg + "\n");
            fileWriter.close();
        }
    }
    
    /*
    * SBI-18
    * Method to log an Exception object
    * Log the exception message with the stack trace below it
    * <author> Ricky Zhao <riz5034@psu.edu>
    */
    public void log(LogLevel logLevel, Exception exception) throws IOException {
        Writer writer = new StringWriter();
        exception.printStackTrace(new PrintWriter(writer));
        String msg = writer.toString();
        log(logLevel, msg);
    }
    
    /*
    * SBI-18
    * Method to log an Exception object
    * Wrapper class to previous method, but will use 
    * LogLevel.WARN as the logging level
    * <author> Ricky Zhao <riz5034@psu.edu>
    */
    public void log(Exception exception) throws IOException {
        log(LogLevel.WARN, exception);
    }
    
    /*
    * Writes log messages to log.txt file
    * Outputs the file path to log the file
    * @author Ricky Zhao <riz5034@psu.edu>
    */
    public static void main(String args[]) throws IOException {
        System.out.println("Log file location: " + System.getProperty("user.dir") + "/logs.txt");
        logger.log(LogLevel.ALL, "Board created");
        logger.log(LogLevel.DEBUG, "Board created");
        logger.log(LogLevel.INFO, "Board created");
        logger.log(LogLevel.WARN, "Board created");
        logger.log(LogLevel.ERROR, "Board created");
        logger.log(LogLevel.FATAL, "Board created");
        
        try {
            int test = 10/0;
        }
        catch(Exception exception) {
            exception.printStackTrace();
            logger.log(exception);
        }
    }
}

