/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Group 3 
* ID: pxo4
 */
package wheeloffortune;

import java.util.Scanner;
import wheeloffortune.Menu.MenuChoice;

/*
* WheelOfFortune class that uses the methods of other classes
* to run a wheel of fortune game
* <author> Ricky Zhao <riz5034@psu.edu>
* <author> Bill Donovan <wfd5035@psu.edu>
 */
public class WheelOfFortune {

    public static void main(String[] args) {
        Menu menu = new Menu("-= Wheel of Fortune =-");
        Wheel wheel = new Wheel();
        String puzzleToSolve = "PENN STATE ABINGTON";
        Puzzle puzzle = new Puzzle(puzzleToSolve);
        Board board = new Board(puzzleToSolve);
        Player player = new Player();
        // add board class

        /*
         * Creates a menu by traversing through all values of MenuChoice
         * and adding them to the _choices ArrayList
         * @author Ricky Zhao <riz5034@psu.edu>
         */
        for (MenuChoice choice : MenuChoice.values()) {
            menu.choices.add(choice.toString());
        }

        menu.info.add("Spin the wheel");
        menu.info.add("Buy a vowel");
        menu.info.add("Solve the puzzle");
        menu.info.add("Help");
        menu.info.add("Quit");

        System.out.println("");

        boolean validInput = false;
        boolean exit = false;

        while (!exit) {
            System.out.println(menu.title);
            System.out.println("");
            board.display(player);
            System.out.println("");
            menu.displayMenu();
            Menu.MenuChoice menuChoice = menu.chooseMenuChoice(menu, wheel);

            switch (menuChoice) {
                case SPIN:
                    Wedge wedge = wheel.spin();
                    String value = wedge.getWedgeValue();
                    System.out.println("Value: " + value);

                    /**
                     * SBI 27 As a user, I want the Bankrupt wedge to zero-out
                     * my winnings, so there is more risk in the game Changed
                     * the WheelOfFortune class so that if the player lands
                     * onBankrupt, then their winnings become $0
                     *
                     * @author Shakhawat Hossain <sxh599@psu.edu>
                     */
                    if (value == "BANKRUPT") {
                        player.setWinningsToBankrupted(0);
                        System.out.println("You landed on BANKRUPT\n"
                                + "You lose all your winnings!");
                    }

                    /*
                    * SBI-28 
                    * If the player lands on lose a turn, it is displayed
                    * but nothing happens
                    * @author Ricky Zhao <riz5034@psu.edu>
                     */
                    if (value == "LOSE A TURN") {
                        System.out.println("You landed on LOSE-A-TURN");
                    }

                    /*
                     * SBI-16
                     * When a player spins the wheel and lands on a money wedge,
                     * prompt for a consonant
                     * Ensure that is is only a consonant
                     * If that consonant has already been guessed, reprompt the user
                     * If the letter is in the puzzle, increase the player's winnings
                     * by the wedge amount
                     * <author> Ricky Zhao <riz5034@psu.edu>
                     * <author> Bill Donovan <wfd5035@psu.edu>
                     */
                    if (value.charAt(0) == '$') {
                        String intString = value.substring(1, value.length());
                        int wedgeValue = Integer.parseInt(intString);
                        validInput = false;
                        char input;

                        do {
                            input = UserInput.readLetter();
                            input = Character.toUpperCase(input);
                            Letters userInput = new Letters(input);

                            if (userInput.isConsonant(input)) {
                                if (puzzle.isGuessed(input)) {
                                    System.out.println("Letter has already been guessed.");
                                } else {
                                    board.updateGuessedLetter(input);
                                    boolean inPuzzle = puzzle.guess(input);
                                    if (inPuzzle) {
                                        System.out.println("CORRECT!");
                                        player.setWinnings(wedgeValue * board.numberOfLetters(input));
                                    } else {
                                        System.out.println("WRONG!");
                                    }
                                    puzzle.guesses.add(input);
                                    validInput = true;
                                }
                                board.updatePlayerBoard(input);
                            }
                        } while (!validInput);

                        System.out.println("Letters guessed:");
                        for (char letter : puzzle.guesses) {
                            System.out.print(letter + " ");
                        }
                        System.out.println("");
                    }
                    break;
                case BUY:
                    /*
                     * SBI - 17
                     * Guess a vowel instead of winning to try to reveal
                     * more letters
                     * Can guess if player has $250 or more
                     * and there are more vowels to be revealed
                     * If chosen vowel has been guessed, reprompt user
                     * <author> Ricky Zhao <riz5034@psu.edu>
                     * <author> Bill Donovan <wfd5035@psu.edu>
                     */
                    int counter = 0;
                    for (char letter : puzzle.guesses) {
                        if (letter == 'A') {
                            counter++;
                        } else if (letter == 'E') {
                            counter++;
                        } else if (letter == 'I') {
                            counter++;
                        } else if (letter == 'O') {
                            counter++;
                        } else if (letter == 'U') {
                            counter++;
                        }
                    }

                    if (player.getWinnings() >= 250 && counter < 5) {
                        validInput = false;
                        char input;

                        do {
                            input = UserInput.readLetter();
                            input = Character.toUpperCase(input);
                            Letters userInput = new Letters(input);

                            if (userInput.isVowel(input)) {
                                if (puzzle.isGuessed(input)) {
                                    System.out.println("Vowel has already been guessed.");
                                } else {

                                    board.updateGuessedLetter(input);
                                    boolean inPuzzle = puzzle.guess(input);
                                    if (inPuzzle) {
                                        System.out.println("CORRECT!");
                                    } else {
                                        System.out.println("WRONG!");
                                    }
                                    board.updatePlayerBoard(input);
                                    puzzle.guesses.add(input);
                                    player.setWinnings(-250);
                                    validInput = true;
                                }
                            }
                        } while (!validInput);
                    } else if (player.getWinnings() < 250) {
                        System.out.println("Insufficient funds");
                    } else if (counter == 5) {
                        System.out.println("All vowels have been guessed");
                    }
                    break;
                /**
                 * SBI-29
                 *
                 * @author Arnold Adu-Darko <aka5317@psu.edu>
                 * This case allows a player to guess the complete puzzle and
                 * tells them whether their guess is correct or not.
                 */
                case SOLVE:
                    Scanner in = new Scanner(System.in);
                    System.out.print("\nSolve the puzzle:");
                    String input = in.nextLine();

                    if (puzzle.isCorrectSolution(input)) {
                        System.out.println("\nYou are correct!");
                        System.out.println("Your won $" + player.getWinnings() + "!");
                        exit = true;
                    } else {
                        System.out.println("\nYou are incorrect!");
                        System.out.println("The puzzle was: " + puzzleToSolve);
                        System.out.println("So you get NOTHING! You LOSE! Good DAY, sir!");
                        exit = true;
                    }
                    break;

                case HELP:
                    /**
                     * SBI 26 here it will call for help text every time user
                     * selects "help"
                     *
                     * @author Shakhawat Hossain<sxh599@psu.edu>
                     */
                    displayHelp();
                    break;

                case QUIT:
                    break;
            }
            System.out.println("");

            if (menuChoice == Menu.MenuChoice.QUIT) {
                exit = true;
            }
        }
    }

    /**
     * SBI 26 Help menu Call it when the player chooses Help from the menu Our
     * help message will match exactly what you see in the project requirements
     *
     * @author Shakhawat Hossain<sxh599@psu.edu>
     *
     */
    private static void displayHelp() {
        System.out.println("\n"
                + "===============================================\n"
                + "       -= Help for \"Wheel of Fortune\" =-\n"
                + "       \n"
                + "The player has these options from the menu:\n"
                + "- spin the wheel and call a consonant\n"
                + "- buy a vowel for $250\n"
                + "- solve the puzzle\n"
                + "- display help\n"
                + "- quit the game\n"
                + "\n"
                + "Each consonant is worth the cash value of the\n"
                + "wedge the wheel lands on.\n"
                + "\n"
                + "If the player lands on Bankrupt their winnings\n"
                + "drop to zero.\n"
                + "\n"
                + "Not implemented yet:\n"
                + "- Multiple players\n"
                + "- Lose a turn\n"
                + "===============================================");
    }
}
