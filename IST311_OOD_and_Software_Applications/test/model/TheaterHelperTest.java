/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
public class TheaterHelperTest {

    public TheaterHelperTest() {
    }

    /**
     * SBI-10: As a developer, I want to test that a non-reserved seat can be
     * reserved, so I know the code works
     *
     * @author Shakhawat Hossain
     */
    @Test
    public void test_reserveSeat_reserveOpenSeat() {

        Seat reservingSeat = new Seat("A", 1);
        Showing showing = (new FakeDataStorage()).getShowings().get(0);
        List<Seat> expSeats = showing.getTheater().getUnavailableSeats();
        TheaterHelper.reserveSeat(showing, reservingSeat);

        for (int i = 0; i < expSeats.size(); i++) {
            assertEquals(reservingSeat.getSeatRow(), expSeats.get(i).getSeatRow());
            assertEquals(reservingSeat.getSeatColumn(), expSeats.get(i).getSeatColumn());
        }
    }

    /**
     * SBI-11: As a developer, I want to test that a reserved seat cannot be
     * reserved again, so I know the code works
     *
     * @author Shakhawat Hossain
     */
    @Test
    public void Test_reserveSeat_canNotBeReserved() {
        Seat reservingSeat = new Seat("A", 1);
        Showing showing = (new FakeDataStorage()).getShowings().get(0);
        List<Seat> expSeats = showing.getTheater().getAvailableSeats();

        TheaterHelper.reserveSeat(showing, reservingSeat);

        for (int i = 0; i < expSeats.size(); i++) {
            if (reservingSeat.getSeatRow().equals(expSeats.get(i).getSeatRow())) {
                if (reservingSeat.getSeatColumn() == expSeats.get(i).getSeatColumn()) {
                    fail("reserve seat can be reserved again!");
                }
            }
        }
    }
}
