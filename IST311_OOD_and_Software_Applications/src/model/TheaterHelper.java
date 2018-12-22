package model;

import java.util.List;

/**
 * SBI-03 As a developer, I want to search for open seats for the showing of a
 * movie, so I can know what is available
 *
 * this is a TheaterHelper class which will help us to view available seat,
 * unavailable seat
 *
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
public class TheaterHelper {

    private static IDataStorage _storage = new FakeDataStorage();

    /**
     * SBI-03 Returns a list of open seats
     *
     * @param showing the showing
     * @return the list of open seats
     */
    public static List<Seat> getOpenSeats(Showing showing) {
        List<Seat> seats = showing.getTheater().getAvailableSeats();
        for (Seat seat : seats) {
            System.out.println("Seat Row: " + seat.getSeatRow() + "\tColumn: " + seat.getSeatColumn());
        }
        return showing.getTheater().getAvailableSeats();
    }

    public static List<Seat> getReservedSeats(Showing showing) {
        List<Seat> seats = showing.getTheater().getUnavailableSeats();
        for (Seat seat : seats) {
            System.out.println("Seat Row: " + seat.getSeatRow() + "\tColumn: " + seat.getSeatColumn());
        }
        return showing.getTheater().getUnavailableSeats();
    }

    public static List<Seat> getAll(Theater theater) {
        List<Seat> seats = theater.getAllSeats();
        for (Seat seat : seats) {
            System.out.println("Seat Row: " + seat.getSeatRow() + "\tColumn: " + seat.getSeatColumn());
        }
        return theater.getAllSeats();
    }

    /**
     * SBI-04 Reserve a seat for a showing
     *
     * @param showing the showing
     * @param seat
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * @author Travis Quimby <trq5001@psu.edu>
     */
    public static void reserveSeat(Showing showing, Seat seat) { // added @param seat for user input
        List<Seat> seats = showing.getTheater().getAvailableSeats();
        for (int i = 0; i < seats.size(); i++) {
            if (seat.getSeatRow().equals(seats.get(i).getSeatRow())) {
                if (seat.getSeatColumn() == seats.get(i).getSeatColumn()) {
                    showing.getTheater().addUnavailableSeat(seats.get(i));
                } else {
                    System.out.println("Booked");
                    break;
                }
            } else {
                System.out.println("Booked");
                break;
            }
        }
    }

    /**
     * SBI-05 Unreserve a seat for a showing
     *
     * @param showing the showing
     * @param seat
     *
     * @author Travis Quimby <trq5001@psu.edu>
     * @author Shakhawat Hossain<sxh599@psu.edu>
     */
    public static void unreserveSeat(Showing showing, Seat seat) {
        List<Seat> seats = showing.getTheater().getUnavailableSeats();
        for (int i = 0; i < seats.size(); i++) {
            if (seat.getSeatRow().equals(seats.get(i).getSeatRow())) {
                if (seat.getSeatColumn() == seats.get(i).getSeatColumn()) {
                    showing.getTheater().addAvailableSeat(seats.get(i));
                } else {
                    System.out.println("Seat is not Booked");
                    break;
                }
            } else {
                System.out.println("Seat is not Booked");
                break;
            }
        }
    }
}
