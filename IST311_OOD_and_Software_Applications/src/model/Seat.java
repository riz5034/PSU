package model;

/**
 * we will use Seat.java to represent a single seat
 *
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
public class Seat {

    // Seat row ex. A, B, C, D
    private String seatRow;
    // Seat column ex. 1. 2. 3.
    private int seatColumn;

    /**
     * Seat Constructor
     *
     * @param seatRow
     * @param seatColumn
     * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public Seat(String seatRow, int seatColumn) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
    }

    /**
     * get SeatRow letter
     * @return seatRow
     * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public String getSeatRow() {
        return seatRow;
    }

    /**
     * set Seat row Letter
     * @param seatRow 
     * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    /**
     * get Seat column
     * @return seatColumn
     * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public int getSeatColumn() {
        return seatColumn;
    }

    /**
     * set Seat column
     * @param seatColumn 
     * @author Shakhawat Hossain <sxh599@psu.edu>
     */
    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

}
