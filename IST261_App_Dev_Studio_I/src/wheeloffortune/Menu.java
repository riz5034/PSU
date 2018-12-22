/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Ricky Zhao 
* ID: riz5034
 */
package wheeloffortune;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/*
* The Menu class is used to display a numbered menu to the user
* The user will be able to input a choice and have the
* appropriate menu choice displayed
* @author Ricky Zhao <riz5034@psu.edu>
 */
public final class Menu {

    /*
    * Enumerate used to ensure typesafety in user input
    * Represents the different menu choices a user can select
    * @author Ricky Zhao <riz5034@psu.edu>
     */
    enum MenuChoice {
        SPIN,
        BUY,
        SOLVE,
        HELP,
        QUIT
    }

    List<String> choices;
    List<String> info;
    String title;
    private Scanner _keyboard;

    /*
    * Constructor to set default values
    * @author Ricky Zhao <riz5034@psu.edu>
     */
    public Menu(String title) {
        this.choices = new ArrayList<>();
        this.info = new ArrayList<>();
        this.title = title;
        this._keyboard = new Scanner(System.in);
    }

    /*
    * Displays the menu to the user
    * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void displayMenu() {
        for (int i = 0; i < info.size(); i++) {
            System.out.println(i + 1 + ")" + info.get(i));
        }
    }

    /*
    * Reads a choice from the menu and returns the menu choice
    * @author Ricky Zhao <riz5034@psu.edu>
     */
    public MenuChoice chooseMenuChoice(Menu menu, Wheel wheel) {
        _keyboard = new Scanner(System.in);
        boolean isInt = false;

        System.out.print("\nEnter choice: ");
        String input = _keyboard.nextLine();

        /**
         * Extra: try catch when user input is not an Integer when user is not
         * in menu range when it throws error this will catch the error and ask
         * for the correct input until user input is correct
         *
         * @author Shakhawat Hossain
         */
        do {
            try {
                new Integer(input);
                isInt = true;
            } catch (NumberFormatException notInteger) {
                try {
                    new Double(input);
                    System.out.println("Please enter a number between 1 to 5");
                    input = _keyboard.nextLine();
                } catch (NumberFormatException notInteger2) {
                    System.out.println("Please enter a number between 1 to 5");
                    input = _keyboard.nextLine();
                }
            }

        } while (!isInt);

        int choice = Integer.parseInt(input);
        Menu.MenuChoice menuChoice = Menu.MenuChoice.values()[choice - 1];
        return menuChoice;

    }

    public static void main(String args[]) {
        Menu menu = new Menu("Menu");
        Wheel wheel = new Wheel();
        menu.displayMenu();
        menu.chooseMenuChoice(menu, wheel);
    }
}
