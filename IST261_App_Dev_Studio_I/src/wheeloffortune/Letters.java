/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Shakhawat Hossain
* ID: sxh599
*/
package wheeloffortune;

/**
 *
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
import java.util.Scanner;

/**
 * @author Shakhawat Hossain <sxh599@psu.edu>
 * this class will take user input and determine if it is a vowel or a consonant
 */

public class Letters{
    
    //@author Shakhawat Hossain <sxh599@psu.edu>
    public char letter;
    private boolean isVowel = false;
    private boolean isConsonant = false;

    public Letters(char ch){
        this.letter = ch;

    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * boolean isVowel(char)
     * if user input is a vowel it will set to true
     * otherwise isVowel = false
     * @param ch
     * @return isVowel(boolean)
     */
    boolean isVowel(char ch){
        char charUpper = Character.toUpperCase(ch);
        if (charUpper == 'A' || charUpper == 'E' || charUpper == 'I' || charUpper == 'O' || charUpper == 'U'){
                System.out.println("Letter is a vowel");
                isVowel = true;
        }else{
                System.out.println("Letter is not a vowel");
                isVowel = false;
        }
        return isVowel;
    }
    
    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * boolean isConsonant(char)
     * if user input is a Consonant it will set to true
     * otherwise isConsonant = false
     * @param ch
     * @return isConsonant(boolean)
     */

    public boolean isConsonant(char ch){
        char charUpper = Character.toUpperCase(ch);
        if (charUpper == 'A' || charUpper == 'E' || charUpper == 'I' || charUpper == 'O' || charUpper == 'U'){
                System.out.println("Letter is NOT a Consonant");
                isConsonant = false;
        }else{
                System.out.println("Letter is a Consonant");
                isConsonant = true;
        }
        return isConsonant;

    }


    
    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * main method
     * takes userInput 
     * type of input is Char
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("enter a letter: ");
        char input = in.next().charAt(0);
        Letters userLetter = new Letters(input);

        /**
         * @author Shakhawat Hossain <sxh599@psu.edu>
         * calls the isConsonant and pass the input
         * prints out the boolean value
         */
        userLetter.isConsonant(input);
        System.out.println(userLetter.isConsonant);

        userLetter.isVowel(input);
        System.out.println(userLetter.isVowel);




    }
	
}

