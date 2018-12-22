package model;

import java.util.ArrayList;
import java.util.List;

/**
 * SBI-06, SBI-07, SBI-08, SBI-09 Cart will be used to used to represent a
 * shopping cart It will holding showings and foods
 *
 * @author Travis Quimby
 * @author Ricky Zhao <riz5034@psu.edu>
 * @author Zachary Sigamony
 * @author Subin Shaji
 */
public class Cart {

    private List<Showing> _showings = new ArrayList();
    private List<Food> _foods = new ArrayList();
    private double _totalCost = 0;//SBI-09 to what the total cost of the cart

    /**
     * SBI-08
     * Returns list of foods
     *
     * @return the list of foods
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public List<Food> getFoods() {
        for (Food food : _foods) {
            System.out.print("Food: " + food.getDescription());
            System.out.print("\tPrice: $" + food.getPrice());
            System.out.print("\tSize: " + food.getSize());
            System.out.println("");
        }

        return _foods;
    }

    /**
     * SBI-06
     * Returns list of Showings
     *
     * @return the list of showings
     * @author Subin Shaji
     */
    public List<Showing> getShowings() {
        for (Showing showing : _showings) {
            System.out.print("Time: " + showing.getTime());
            System.out.print("\tMovie: " + showing.getMovie().getTitle());
            System.out.print("\tTheater: " + showing.getTheater().getName());
            System.out.print("\tCost: " + showing.getCost());
        }

        return _showings;
    }

    /**
     * SBI-06
     * Adds a food to the cart
     *
     * @param food the food to add to the cart
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void add(Food food) {
        _foods.add(food);
        _totalCost += food.getPrice();
    }
    
    /**
     * SBI-06 Adds a showing to the cart
     *
     * @param showing the showing to add to the cart
     * @author Subin Shaji
     */
    public boolean add(Showing showing, int quantity) {
        if (!showing.getSoldOut()) {
            if (quantity <= showing.getTheater().getAvailableSeats().size()) {
                _showings.add(showing);
                _totalCost += showing.getCost();
                return true;
            }
            else {
                System.out.println("Error: Not enough seats left");
                return false;
            }
        } else {
            System.out.println("Error: Showing Sold Out");
            return false;
        }

    }

    //SBI07 - Remove showing from the list and subtract the total down
    public void remove(Showing showing) {
        _showings.remove(showing);
        _totalCost = _totalCost - showing.getCost();
    }

    //SBI07 - Revome food from the list and subtract the total down
    public void remove(Food food) {
        _foods.remove(food);
        _totalCost = _totalCost - food.getPrice();
    }

    //SBI09 To print the ticket price and the total
    public double getTotal() {
        return _totalCost;
    }
    
    //Test class method - Zack
    public static void main(String[] args){
        
    }
}
