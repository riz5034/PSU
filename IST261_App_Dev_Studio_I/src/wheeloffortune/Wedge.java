/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Ricky Zhao, Bill Donovan
* ID: riz5034, wfd5035
*/
package wheeloffortune;

/*
* The Wedge class is used to hold a number and value of a wedge in a wheel
* @author Ricky Zhao <riz5034@psu.edu>
* @author Bill Donovan <wfd5035@psu.edu>
*/
public class Wedge {
    private int _number;
    private String _value;
    
    /*
    * Constructor to give default values to a wedge
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Bill Donovan <wfd5035@psu.edu>
    */
    public Wedge(int num, String val) {
        _number = num;
        _value = val;
    }
    
    /*
    * Getter method to return wedge number
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Bill Donovan <wfd5035@psu.edu>
    */
    int getWedgeNumber() {
        return _number;
    }
    
    /*
    * Getter method to return wedge value
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Bill Donovan <wfd5035@psu.edu>
    */
    String getWedgeValue() {
        return _value;
    }
    
    public static void main(String[] args) {
        Wedge test = new Wedge(1, "test");
        System.out.println(test.getWedgeNumber());
        System.out.println(test.getWedgeValue());
        
    }
}
