/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author riz5034
 */
public class Showing {
    private String _time;
    private Movie _movie;
    private Theater _theater;
    private double _cost;
    private boolean _soldOut;
    
    public Showing(String time, Movie movie, Theater theater, double cost) {
        this._time = time;
        this._movie = movie;
        this._theater = theater;
        this._cost = cost;
        this._soldOut = false;
    }
    
   public String getTime() {
       return this._time;
   }
   
   public void setTime(String time) {
       this._time = time;
   }
       
   public Double getCost() {
       return this._cost;
   }
   
   public void setCost(double cost) {
       this._cost = cost;
   }
   public Movie getMovie() {
       return this._movie;
   }
   
   public void setMovie(Movie movie) {
       this._movie = movie;
   }
   
   public Theater getTheater() {
       return this._theater;
   }
   
   public void setTheater(Theater theater) {
       this._theater = theater;
   }
   
   public boolean getSoldOut() {
       return this._soldOut;
   }
   
   public void setSoldOut(boolean soldOut) {
       this._soldOut = soldOut;
   }
   
   
}
