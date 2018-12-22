/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Travis Quimby <trq5001@psu.edu>
 */
public class CartHelperTest {
    
    public CartHelperTest() {
    }

    /**
     * SBI-16
     * As a developer, I want to test that if I put a food item in the cart and
     * remove it, then the cart is empty, so I know the code works
     * @author Travis Quimby <trq5001@psu.edu>
     */
    @Test
    public void testAddRemoveFoodCartEmpty(){
        
        List<Food> testFood = new ArrayList<Food>();
        IDataStorage storage = new FakeDataStorage();
        Cart cart = new Cart();
        cart.add(storage.getFoods().get(0));
        cart.remove(storage.getFoods().get(0));
        
        assertEquals("Cart should be empty", cart.getFoods(), testFood);
    }
    
    /**
     * SBI-17
     * As a developer, I want to test that if I put 3 food items in the cart 
     * that the total amount is correct, then the cart is empty, so I know the 
     * code works
     * @author Travis Quimby <trq5001@psu.edu>
     */
    
    @Test
    public void testAddThreeFoodItemsToCart(){
    
        IDataStorage storage = new FakeDataStorage();
        Cart cart = new Cart();
        cart.add(storage.getFoods().get(0));
        cart.add(storage.getFoods().get(1));
        cart.add(storage.getFoods().get(2));
        
        double totalCost = (storage.getFoods().get(0).getPrice() + 
                storage.getFoods().get(1).getPrice() + 
                storage.getFoods().get(2).getPrice());
        
        assertEquals("Prices should be equal", 15.97, totalCost, 0);
        
    }
    
    /**
    * SBI - 18
    * As a developer, I want to test that I cannot add a showing to the cart if it's sold out, so I know the code works 
    * @author Ricky Zhao <riz5034@psu.edu>
    */
    @Test
    public void test_add_GetsSoldOutShowing() {
        IDataStorage storage = new FakeDataStorage();
        Cart cart = new Cart();
        cart.add(storage.getShowings().get(0), 1);
        assertEquals("Cart should be empty", 0, cart.getShowings().size());
    }
    
    /**
    * SBI - 19
    * As a developer, I want to test that if there is one seat left for a showing, that I can't put 2 of those showings in a cart, so I know the code works
    * @author Ricky Zhao <riz5034@psu.edu>
    */
    @Test
    public void test_add_GetsOverAvailableQuantity() {
        IDataStorage storage = new FakeDataStorage();
        Cart cart = new Cart();
        boolean added = cart.add(storage.getShowings().get(1), 100);
        assertFalse("Cart should not be able to add showings without enough seats", added);
    }
}
