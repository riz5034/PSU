/*
 *This class stores the puzzle answer and a user's guesses in arrayLists. It will
 * then use a boolean to check if the user's input is in the puzzle or not.
 */
package wheeloffortune;

/**
 *
 * @author Arnold Adu-Darko <aka5317@psu.edu>
 */
import java.util.ArrayList;

/**
 *
 * @author Arnold Adu-Darko <aka5317@psu.edu>
 * @author Ricky Zhao <riz5034@psu.edu>
 * This class is where the puzzle and all user guesses are stored.
 */
public class Puzzle {

    ArrayList<String> solved;
    static ArrayList<Character> guesses;
    private String _puzzle;

    Puzzle(String puzzle) {
        solved = new ArrayList<>();
        guesses = new ArrayList<>();
        _puzzle = puzzle;
    }

    /**
     *
     * @author Arnold Adu-Darko <aka5317@psu.edu>
     * @author Ricky Zhao <riz5034@psu.edu>
     * This method reads user input by calling readLetter from the UserInput class.
     */
    public static char guessLetter() {
        char letter = UserInput.readLetter();
        return letter;
    }

    /**
     * @author Arnold Adu-Darko <aka5317@psu.edu>
     * SBI 22
     */
    public static char hasGuessed() {
        char letter2 = Puzzle.guessLetter();
        return letter2;
    }

    /**
     *
     * @author Arnold Adu-Darko <aka5317@psu.edu>
     * @author Ricky Zhao <riz5034@psu.edu>
     * This method uses a boolean and a for loop to determine whether a user's input
     * is the puzzle or not.
     * The character is determined if it's in the puzzle or not by going through each 
     * index of the _puzzle string
     * SBI 23
     */
    public boolean guess(char letter) {
        boolean foundLetter = false;

        for (int i = 0; i < _puzzle.length(); i++) {
            if (letter == _puzzle.charAt(i)) {
                foundLetter = true;
            }
        }
        return foundLetter;
    }

    /**
     * @author Arnold Adu-Darko <aka5317@psu.edu>
     * SBI 22
     */
    public boolean isGuessed(char letter2) {
        boolean guessedLetter = false;

        for (int i = 0; i < guesses.size(); i++) {
            if (letter2 == guesses.get(i)) {
                guessedLetter = true;
            }
        }
        return guessedLetter;
    }

    /** 
     * @author Bill Donovan <wfd5035@psu.edu>
     * This method checks if a possible solution is correct, so that can be 
     * used when a player guesses a solution.
     * SBI-25
     */
    public boolean isCorrectSolution(String possibleSolution) {
        boolean isCorrect = false;

        if (possibleSolution.equalsIgnoreCase(_puzzle)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    /**
     *
     * @author Arnold Adu-Darko <aka5317@psu.edu>
     * @author Ricky Zhao <riz5034@psu.edu>
     * This method stores the actual puzzle answer and prints out whether a user
     * input is in the puzzle or not.
     * SBI 22
     */
    public static void main(String args[]) {
        Puzzle test = new Puzzle("PENN STATE ABINGTON");
        char guess = guessLetter();
        char guess2 = hasGuessed();

        if (test.guess(guess) || test.guess(guess2)) {
            System.out.println("The letter " + guess + " is in the puzzle!");
        } else if (!test.guess(guess) && !test.guess(guess2)) {
            System.out.println("The letter " + guess + " is not in the puzzle! Please try again -> ");
        }
        if (test.isGuessed(guess2) || test.isGuessed(guess)) {
            System.out.println(guess2 + " has already been guessed.");
        }

        guesses.add(guess);
        guesses.add(guess2);

        System.out.println("Letters guessed: ");
        for (char letters : guesses) {
            System.out.println(letters);
        }
    }
}
