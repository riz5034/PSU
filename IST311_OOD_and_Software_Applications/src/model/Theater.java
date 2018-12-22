/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 * SBI -04 As a developer, I want to reserve a seat for a showing, so a customer has a place to sit
 * 
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
public class Theater {
    private String _name;
    private ArrayList<Seat> _seats;
    private ArrayList<Seat> _availableSeats = new ArrayList<Seat>();
    private ArrayList<Seat> _unavailableSeats = new ArrayList<Seat>();

    public Theater(ArrayList<Seat> seats) {
        _seats = seats;
        _availableSeats = _seats;
    }
    
    public String getName() {
        return this._name;
    }
    
    public void setName(String name) {
        this._name = name;
    }

    public ArrayList<Seat> getAllSeats() {
        return this._seats;
    }
    
    public void setAllSeats(ArrayList<Seat> seats) {
        this._seats = seats;
    }
    
    public ArrayList<Seat> getAvailableSeats() {
        return this._availableSeats;
    }
    
    /**
     * SBI-03 As a developer, I want to search for open seats for the showing of a movie, so I can know what is available
     * @author Mibin Baby mqb5739@psu.edu
     */


    public void setAvailableSeats(ArrayList<Seat> availableSeats) {
        this._availableSeats = availableSeats;
    }
    
    /**
     * SBI-04
     * Add a seat to the available seat list
     * @param seat the seat to add
     * @author Shakhawat Hossain <sxh599@psu.edu>
     * @author Travis Quimby <trq5001@psu.edu>
     */
    public void addAvailableSeat(Seat seat) {
        this._unavailableSeats.remove(seat);
        this._availableSeats.add(seat);
    }

    public ArrayList<Seat> getUnavailableSeats() {
        return this._unavailableSeats;
    }

    public void setUnavailableSeats(ArrayList<Seat> unavailableSeats) {
        this._unavailableSeats = unavailableSeats;
    }
    
    /**
     * SBI-05
     * Add a seat the the unavailable seat list
     * @param seat the seat to add
     * @author Travis Quimby <trq5001@psu.edu>
     */
    public void addUnavailableSeat(Seat seat) {
        this._availableSeats.remove(seat);
        this._unavailableSeats.add(seat);
    }

    /* 
    // Changed constructor to accept initial seating
    public static void main(String[] args) {
        Theater theaterSeat = new Theater();

        System.out.println(theaterSeat._seats.size());
    } */
}
