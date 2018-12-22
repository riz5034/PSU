/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * SBI-09
 * @author Shakhawat Hossain <sxh599@psu.edu>
 * @author Zachary Sigamony <zvs5039@psu.edu>
 */
public class Payment {

//    private double _value;
//
//    public double getValue() {
//        return _value;
//    }
//
//    public void setValue(double _value) {
//        this._value = _value;
//    }
    //Private variable of Payment
    private PaymentType _typeOfPayment;
    private double _cost;
    
    //S01/T01 Conatain enum of a Payment type
    public enum PaymentType{
        CASH,
        DEBIT,
        CREDIT;
    }

    
    public Payment(PaymentType typeOfPayment, double cost){
        this._typeOfPayment = typeOfPayment;
        this._cost = cost;
    }
    
    public void getPayment(){
        System.out.println("\tPaid: $"+ String.format("%.2f", _cost)+" \tPaid by: "+_typeOfPayment);
    }
    
    
    //Test the class method - zack
    public static void main(String[] args) {
        Payment payment= new Payment(PaymentType.CASH, 100.01);
        payment.getPayment();
    }
}
