package com.training;

import java.util.List;

import com.training.model.CreditCard;
import com.training.services.CreditCardService;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CreditCard card1 = new CreditCard(47581,"Rakesh",5609991);
		CreditCard card2 = new CreditCard(47582,"Ramesh",5609992);
		CreditCard card3 = new CreditCard(47583,"Roshan",5609993);
		CreditCard card4 = new CreditCard(47584,"Rajesh",5609994);
		
		CreditCardService service = new CreditCardService();
		
		System.out.println(service.add(card1));
		System.out.println(service.add(card2));
		System.out.println(service.add(card3));
		System.out.println(service.add(card4));
		
		List<CreditCard> list =service.findAll();
		for(CreditCard eachCard : list) {
			System.out.println(eachCard);
		}

	}

}
