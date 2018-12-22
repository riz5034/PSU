/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Ricky Zhao
* ID: riz5034
*/
package wheeloffortune;


import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* The Config class is used to read config properties from a file and use
* them to determine the log level to set
* Source:https://stackoverflow.com/questions/7665310/how-to-use-config-properties-file-throughout-the-classes/7665341#7665341
* @author Ricky Zhao <riz5034@psu.edu> 
*/
public final class Config {
    private static final Properties properties = new Properties();
    
    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            properties.load(loader.getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    /*
    * Reads the log level from a file
    * @author Ricky Zhao <riz5034@psu.edu>
    */
    public static String getSetting(String key) {
        return properties.getProperty(key);
    }
    
    public static void main(String args[]) {
        System.out.println("test");
    }
}