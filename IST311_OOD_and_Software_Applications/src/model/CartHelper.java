package model;


/**
 *Hold methods related to cart, that is interact with the user
 * 
 * @author Zachary Sigamony <zvs5039@psu.edu>
 */
public class CartHelper {
    
    
    //SBI-09 Purchase complete print the ticket 
    public static void checkout(Cart cart, Payment payment){
        System.out.println("************Team 4 Movie Theater************");
        cart.getShowings();//The reason I am not taking a return due print happening in this method already
        cart.getFoods();//The reason I am not taking a return due print happening in this method already
        System.out.print("Total Cost: $" + String.format("%.2f", cart.getTotal()));
        payment.getPayment();
        System.out.println("Thank you");
    }
    
    //To test the method in the Class
    public static void Main(String[] args){
        
    }
}
