/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Arnold Adu-Darko 
* ID: aka5317
*/

/*
 * This program prompts a user/player to enter a single letter and try to guess 
 * a puzzle. The program reads the input via the Scanner then checks the length 
 * of the user input to make sure only a single letter has been entered using 
 * the while loop. While the input meets the greater than 1 condition, it prints
 * out an error message and prompts the user/player to enter a valid input.
 */
package wheeloffortune;

/**
 * @author Arnold Adu-Darko <aka5317@psu.edu>
 */
import java.util.Scanner;

public class UserInput {

    /** 
      @author Arnold Adu-Darko <aka5317@psu.edu>
      This is the main method that calls readLetter
     */
    public static void main(String args[]) 
    {
        char guess = readLetter();
        System.out.println(guess);
    }

    /**
      @author Arnold Adu-Darko <aka5317@psu.edu>
      This is the readLetter method which prompts the user for an input, checks
      to make sure the input is a single letter, then returns the input.
     */
    public static char readLetter() 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a single letter to try and guess the puzzle -> ");
        String input = in.nextLine();
        
        while (input.length() > 1 || !input.matches("^[a-zA-Z]*$")) 
        {
            System.out.print("Input is invalid. Please enter single letter ->");
            input = in.nextLine();
        }
        return input.toUpperCase().charAt(0);
    }
    
}
