/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * SBI-08
 * A food item with a description, price, and size
 * FoodType enum will be either POPCORN or SODA
 * FoodSize enum is S, M, or L
 * 
 * @author Ricky Zhao <riz5034@psu.edu>
 * @author Travis Quimby
 */
public class Food {
    // Type of food
    public enum FoodType {
        POPCORN,
        SODA
    }
    
    // Size of food
    public enum FoodSize {
        S,
        M,
        L
    }

    private FoodType _description;
    private double _price;
    private FoodSize _size;
    
    /**
     * Food constructor 
     * 
     * @param description the description of the food
     * @param price the price of the food
     * @param size the size of the food
     */
    public Food(FoodType description, double price, FoodSize size) {
        this._description = description;
        this._price = price;
        this._size = size;
    }
    
    /**
     * Get the description of the food
     * @return the description of the food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public FoodType getDescription() {
        return this._description;
    }
    
    /**
     * Set the description of the food
     * @param description the description of the food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void setDescription(FoodType description) {
        this._description = description;
    }
    
    /**
     * Get the price of the food
     * @return the price of the food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public double getPrice() {
        return this._price;
    }
    
    /**
     * Set the price of the food
     * @param price the price of the food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void setPrice(double price) {
        this._price = price;
    }
    
    /**
     * Get the size of the food
     * @return the size of the food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public FoodSize getSize() {
        return this._size;
    }
    
    /**
     * Set the size of the food
     * @param size the size of the food
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public void setSize(FoodSize size) {
        this._size = size;
    }
    
    /**
     * Main method used to test the food class
     * @param args 
     * @author Ricky Zhao <riz5034@psu.edu>
     */


    public static void main(String[] args) {
        Food test1 = new Food(FoodType.POPCORN, 99.99, FoodSize.S);
        System.out.println(test1.getDescription());
        System.out.println(test1.getPrice());
        System.out.println(test1.getSize());
    }
 }
