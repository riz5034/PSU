/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Ricky Zhao, Bill Donovan
* ID: riz5034, wfd5035
 */
package wheeloffortune;

import java.util.ArrayList;
import java.util.Random;

/*
* SBI-08
* The Wheel class is used to hold a list of wedges that a user can use
* by calling the spin method to return a random wedge
* @author Ricky Zhao <riz5034@psu.edu>
* @author Bill Donovan <wfd5035@psu.edu>
 */
public class Wheel {

    ArrayList<Wedge> wheel;

    /*
    * SBI-08
    * Constructor to create an array list of wedges
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Bill Donovan <wfd5035@psu.edu>
     */
    public Wheel() {
        wheel = new ArrayList<Wedge>();
        wheel.add(new Wedge(1, "$5000"));
        wheel.add(new Wedge(2, "$600"));
        wheel.add(new Wedge(3, "$500"));
        wheel.add(new Wedge(4, "$300"));
        wheel.add(new Wedge(5, "$500"));
        wheel.add(new Wedge(6, "$800"));
        wheel.add(new Wedge(7, "$550"));
        wheel.add(new Wedge(8, "$400"));
        wheel.add(new Wedge(9, "$300"));
        wheel.add(new Wedge(10, "$900"));
        wheel.add(new Wedge(11, "$500"));
        wheel.add(new Wedge(12, "$300"));
        wheel.add(new Wedge(13, "$900"));
        wheel.add(new Wedge(14, "BANKRUPT"));
        wheel.add(new Wedge(15, "$600"));
        wheel.add(new Wedge(16, "$400"));
        wheel.add(new Wedge(17, "$300"));
        wheel.add(new Wedge(18, "LOSE A TURN"));
        wheel.add(new Wedge(19, "$800"));
        wheel.add(new Wedge(20, "$350"));
        wheel.add(new Wedge(21, "$450"));
        wheel.add(new Wedge(22, "$700"));
        wheel.add(new Wedge(23, "$300"));
        wheel.add(new Wedge(24, "$600"));
    }

    /*
    * SBI-08
    * Uses the random class to randomly generate a number between the 
    * wedge number of the first wedge and the wedge number of the last
    * wedge in the array list
    * Returns that wedge
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Bill Donovan <wfd5035@psu.edu>
     */
    Wedge spin() {
        Random random = new Random();
        int max = wheel.size() - 1;
        int min = 0;
        int randomNum = random.nextInt(max + 1 - min) + min;

        return wheel.get(randomNum);
    }

    public static void main(String[] args) {
        Wheel test = new Wheel();
        System.out.println(test.spin().getWedgeNumber());
        System.out.println(test.spin().getWedgeValue());
        System.out.println(test.wheel.size());
    }
}
