/*
 * IST 261
 * Fall 2017
 * Instructor: Phil O'Connell
 * Student: Shakhawat Hossain
 * ID: sxh599
 */
/**
 * The Board.java class utilizes setters and getters to store a puzzle answer,
 * then it asks a user to guess the puzzle by guessing the puzzle word(s). It
 * will then print out the user's input in all caps with a space between every
 * letter.
 */
package wheeloffortune;

/**
 *
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Board stores the puzzle answer.
 */
public class Board {

    private String _answerBoard;
    private String _playerBoard;
    private final ArrayList<Character> _availConsonants;
    private final ArrayList<Character> _availVowels;
    public static Scanner userInput = new Scanner(System.in);

    /*
    * SBI-20
    * Constructur that accepts a String to create the puzzle board
    * Another String is used to display the puzzle to the player
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public Board(String puzzle) {
        setBoard(puzzle);
        _availConsonants = new ArrayList<Character>();
        _availVowels = new ArrayList<Character>();
        setAvailConsonants();
        setAvailVowels();
    }

    /*
    * SBI-24
    * Initializes the available consonants to all consonants
    * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void setAvailConsonants() {
        _availConsonants.add('B');
        _availConsonants.add('C');
        _availConsonants.add('D');
        _availConsonants.add('F');
        _availConsonants.add('G');
        _availConsonants.add('H');
        _availConsonants.add('J');
        _availConsonants.add('K');
        _availConsonants.add('L');
        _availConsonants.add('M');
        _availConsonants.add('N');
        _availConsonants.add('P');
        _availConsonants.add('Q');
        _availConsonants.add('R');
        _availConsonants.add('S');
        _availConsonants.add('T');
        _availConsonants.add('V');
        _availConsonants.add('W');
        _availConsonants.add('X');
        _availConsonants.add('Y');
        _availConsonants.add('Z');
    }

    /*
    * SBI-24
    * Initializes the available vowls to all consonants
    * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void setAvailVowels() {
        _availVowels.add('A');
        _availVowels.add('E');
        _availVowels.add('I');
        _availVowels.add('O');
        _availVowels.add('U');
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * Instantiating _boardPuzzle to puzzle
     */
    public void setPuzzle(String puzzle) {
        setBoard(puzzle);
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * getPuzzle will get the puzzle word from user input trim them if it has
     * unnecessary space(s) adding single space between each letters convert it
     * into upper case!
     */
    public String getPuzzle() {
        return _playerBoard;
    }

    /*
    * SBI-20
    * Method returns a String from a given String by adding 
    * spaces between each letter
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public String createBoard(String puzzle) {
        String noSpacePuzzle = puzzle.trim().replaceAll(" +", " ");
        String addedSpacePuzzle = noSpacePuzzle.replaceAll("", " ");
        String finalPuzzle = addedSpacePuzzle.toUpperCase();
        finalPuzzle = finalPuzzle.substring(1, finalPuzzle.length() - 1);
        return finalPuzzle;
    }

    /*
    * SBI-20 
    * Sets the _answerBoard and _playerBoard from a given String
    * _answerBoard is created from the createBoard method
    * _playerBoard is created from the _answerBoard by converting letters
    * to an underscore
     */
    public void setBoard(String puzzle) {
        _answerBoard = puzzle;
        _answerBoard = createBoard(_answerBoard);
        _playerBoard = "";

        for (int i = 0; i < _answerBoard.length(); i++) {
            if (_answerBoard.charAt(i) >= 'A' && _answerBoard.charAt(i) <= 'Z') {
                _playerBoard += '_';
            } else {
                _playerBoard += ' ';
            }
        }
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * This method simply handles the output to the console by calling getPuzzle
     * and displaying the right answer.
     */
    public void display(Player player) {
        System.out.println(_playerBoard);
        System.out.println("");

        /**
         * SBI 14
         *
         * @author Shakhawat Hossain <sxh599@psu.edu>
         * called display because wanted to display winning amount returns int
         * winning amount
         */
        player.display();
        System.out.println("");

        /*
        * SBI-24
        * Display available Consonants and Vowels not guesses yet
        * @author Ricky Zhao <riz5034@psu.edu>
         */
        System.out.println("Consonants Not Guessed:");
        for (char letter : _availConsonants) {
            System.out.print(letter + " ");
        }
        System.out.println("\n");

        System.out.println("Vowels Not Guessed:");
        for (char letter : _availVowels) {
            System.out.print(letter + " ");
        }
        System.out.println("");
    }

    /*
    * SBI-24
    * Uses isGuessed method from Puzzle class to determine if
    * letter stays in available consonants and vowels
    * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void updateGuessedLetter(char letter) {
        for (int i = 0; i < _availConsonants.size(); i++) {
            if (letter == _availConsonants.get(i)) {
                _availConsonants.remove(i);
            }
        }

        for (int i = 0; i < _availVowels.size(); i++) {
            if (letter == _availVowels.get(i)) {
                _availVowels.remove(i);
            }
        }
    }

    /*
    * SBI-20
    * Updates _playerBoard to display letter if correct
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public void updatePlayerBoard(char letter) {
        String updatedBoard = "";

        for (int i = 0; i < _answerBoard.length(); i++) {
            if (letter == _answerBoard.charAt(i)) {
                updatedBoard += letter;
            } else {
                updatedBoard += _playerBoard.charAt(i);
            }
        }
        _playerBoard = updatedBoard;
    }

    /*
    * SBI-20
    * Method checks how many time a given char appears in the puzzle
    * Checks each index of a String and compares to given char
    * Returns that number
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public int numberOfLetters(char letter) {
        int counter = 0;
        for (int i = 0; i < _answerBoard.length(); i++) {
            if (letter == _answerBoard.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * This is the main method that asks for user input and displays it by
     * calling the Display method.
     */
    public static void main(String[] args) {
        Player player = new Player();
        System.out.println("Board Class");
        System.out.println("Enter a puzzle word(s): ");
        String puzzleWord = userInput.nextLine();
        Board newBoard = new Board(puzzleWord);
        newBoard.display(player);
        newBoard.updatePlayerBoard('T');
        newBoard.display(player);
        newBoard.updatePlayerBoard('E');
        System.out.println(newBoard.numberOfLetters('E'));
        newBoard.display(player);
    }

}
