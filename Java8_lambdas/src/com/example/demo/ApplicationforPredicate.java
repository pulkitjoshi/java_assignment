package com.example.demo;

import com.example.demo.func.ifaces.UsingPredicate;
import com.training.model.CreditCard;

public class ApplicationforPredicate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CreditCard card1 = new CreditCard(1010,"RAMESH",47000);
		CreditCard card2 = new CreditCard(2020,"RAHUL",77000);
		
		UsingPredicate.plainPredicate(card1);
		UsingPredicate.plainPredicate(card2);
		
		
		
	}

}
