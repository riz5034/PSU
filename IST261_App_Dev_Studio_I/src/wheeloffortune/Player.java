/*
* IST 261
* Fall 2017
* Instructor: Phil O'Connell
* Student: Shakhawat Hossain
* ID: sxh599
 */
package wheeloffortune;

/**
 * @author Shakhawat Hossain <sxh599@psu.edu>
 * a class to represent the player, so I can store relevant information
 */
import java.util.Scanner;

public class Player {

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * Initialized the winnings to $1000
     */
    public int winnings;
    private String _playerName;

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * constructor to calculate total amount winnings = $1000 and winAmount is
     * amount won from the Wedge so total amount = winnings + winAmount
     */
    public Player() {
        // chhanged initial value for winnings account to 0.00
        this.winnings = 0;
        _playerName = "";
        boolean validName = false;
        Scanner scanner = new Scanner(System.in);

        while (!validName) {
            System.out.print("Enter player name: ");
            _playerName = scanner.nextLine();

            if (_playerName.length() > 0) {
                int whiteSpaceCounter = 0;

                for (char i : _playerName.toCharArray()) {
                    if (Character.isWhitespace(i)) {
                        whiteSpaceCounter++;
                    }
                }
                if (whiteSpaceCounter != _playerName.length()) {
                    validName = true;
                }
            }
        }
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     *
     */
    public void setWinnings(int otherValue) {
        this.winnings = winnings + otherValue;
    }

    /**
     * SBI 27 set method to set the winnings into 0;
     *
     * @param bankrupted
     * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public void setWinningsToBankrupted(int bankrupted) {
        this.winnings = bankrupted;
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * returns the total amount in account
     */
    public int getWinnings() {
        return winnings;
    }

    /*
    * SBI-21
    * Setter method to _playerName
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public void setPlayerName(String name) {
        _playerName = name;
    }

    /*
    * SBI-21
    * Setter method to _playerName
    * @author Ricky Zhao <riz5034@psu.edu>
    * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public String getPlayerName() {
        return _playerName;
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * display method
     */
    public void display() {
        System.out.println("Player: " + getPlayerName());
        System.out.println("Winnings: $" + getWinnings());
    }

    /**
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * this is a sample output for this class it takes a value from user add it
     * to the players account and display it
     */
    public static void main(String[] args) {
        Player account = new Player();
        account.display();
    }

}
