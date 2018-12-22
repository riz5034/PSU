package movietime04;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cart;
import model.FakeDataStorage;
import model.Food;
import model.Movie;
import model.Theater;
import model.IDataStorage;
import model.Seat;
import model.Showing;
import model.TheaterHelper;
import model.FoodHelper;
import model.MovieHelper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javafx.scene.control.Button;
import model.Payment;
import model.CartHelper;

/**
 *
 * @author Phil O'Connell <pxo4@psu.edu>
 */
public class MovieTime04 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        /**
         * this section is for theater window
         *
         * @author Shakhawat Hossain
         */
        Parent rootTheater = FXMLLoader.load(getClass().getResource("/view/Theater.fxml"));
        Scene sceneTheater = new Scene(rootTheater);

        //stage.setScene(scene);
        stage.setScene(sceneTheater);
        stage.setTitle("Theater Seats");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        // added scanner to grab user input
        Scanner input = new Scanner(System.in);

        // TODO Put your own team number and names here
        System.out.println("Team #4");
        System.out.println("Mibin Baby (mqb5739@psu.edu)");
        System.out.println("Shakhawat Hossain (sxh599@psu.edu)");
        System.out.println("Travis Quimby (trq5001@psu.edu)");
        System.out.println("Subin Shaji (sxs5879@psu.edu)");
        System.out.println("Zachary Sigamony (zvs5039@psu.edu)");
        System.out.println("Ricky Zhao (riz5034@psu.edu)");

        System.out.println("");
        System.out.println("Testing SBI-01: As a developer, I want a fake data "
                + "storage, so I don't need to wait for the database...");
        IDataStorage storage = new FakeDataStorage();
        System.out.println("Complete");
        System.out.println("");

        System.out.println("Testing SBI-02: As a developer, I want to search for"
                + " movies by title substring, so I can filter the full list...");
        System.out.println("Testing for 'airplane'");
        MovieHelper.searchTitle("airplane");
        System.out.println("");

        System.out.println("Testing SBI-03: As a developer, I want to search for"
                + " open seats for the showing of a movie, so I can know what is "
                + "available...");
        Showing showing = storage.getShowings().get(0);
        TheaterHelper.getOpenSeats(showing);
        System.out.println("");

        System.out.println("Testing SBI-04: As a developer, I want to reserve a "
                + "seat for a showing, so a customer has a place to sit...");
        System.out.println("Printing open seats");
        TheaterHelper.getOpenSeats(showing);
        System.out.println("Enter Seat Row: ");
        String seatRow = input.nextLine();
        System.out.println("Enter Seat column number");
        int seatColumn = input.nextInt();
        TheaterHelper.reserveSeat(showing, new Seat(seatRow, seatColumn));
        System.out.println("Printing open seats");
        TheaterHelper.getOpenSeats(showing);

        System.out.println("Enter Seat Row: ");
        String seatRow1 = input.next();
        System.out.println("Enter Seat column number");
        int seatColumn1 = input.nextInt();
        TheaterHelper.reserveSeat(showing, new Seat(seatRow1, seatColumn1));
        System.out.println("Printing open seats");
        TheaterHelper.getOpenSeats(showing);
        System.out.println("");

        System.out.println("Testing SBI-05: As a developer, I want to un-reserve"
                + " a seat for a showing, so it's available to a customer...");
        System.out.println("Printing Reserved seats");
        TheaterHelper.getReservedSeats(showing);
        System.out.println("Un-reserving Seat");
        System.out.println("Enter Seat Row: ");
        String seatRowUnreserve = input.next();
        System.out.println("Enter Seat column number");
        int seatColumnUnreserve = input.nextInt();
        TheaterHelper.unreserveSeat(showing, new Seat(seatRowUnreserve, seatColumnUnreserve));
        TheaterHelper.getOpenSeats(showing);
        System.out.println("");

        System.out.println("Testing SBI-06: As a user, I want to add showings "
                + "and food to a cart, so I can purchase them later...");
        Cart cart = new Cart();
        System.out.println("Adding food to cart");
        cart.add(storage.getFoods().get(0));
        cart.add(storage.getFoods().get(1));
        cart.getFoods();
        System.out.println("Adding showing to cart");
        cart.add(showing, 1);
        cart.getShowings();
        System.out.println("\n");

        System.out.println("Testing SBI-07: As a user, I want to remove showings"
                + " or food from a cart, in case I change my mind...");
        System.out.println("Removing large popcorn from cart");
        cart.remove(storage.getFoods().get(1));
        cart.getFoods();
        System.out.println("Removing showing from cart");
        cart.remove(showing);
        cart.getShowings();
        System.out.println("");

        System.out.println("Testing SBI-08: As a user, I want to see a list of "
                + "food, so I can add it to my cart...");
        FoodHelper.getAll();
        System.out.println("");

        System.out.println("Testing SBI-09: As a user, I want to purchase the "
                + "items in my cart, so I can get in to the theater...");
        Payment payment = new Payment(Payment.PaymentType.CASH, cart.getTotal());
        CartHelper.checkout(cart, payment);
        System.out.println("");

        System.out.println("Testing search for showing...");
        MovieHelper.searchShowing("the");
        System.out.println("");

        /**
         * This is testing the comparison between two objects When using the ==
         * operator, the reference/address of the two objects are being
         * compared. If the two objects are created using new, then despite
         * having the same exact fields, they will not be considered equal
         */
        System.out.println("Testing comparison of two objects");
        Seat seat1 = new Seat("A", 1);
        Seat seat2 = new Seat("A", 1);

        System.out.println("Comparing using '=='");
        if (seat1 == seat2) {
            System.out.println("Same seats");
        }
    }
}
