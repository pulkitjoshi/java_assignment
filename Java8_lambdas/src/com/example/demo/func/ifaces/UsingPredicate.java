package com.example.demo.func.ifaces;
import java.util.function.Predicate;
import java.util.function.DoublePredicate;
import java.util.function.BiPredicate;
import com.training.model.CreditCard;

public class UsingPredicate {
	
	public static void plainPredicate(CreditCard card) {
		
		Predicate<CreditCard> checkLimit = ccard -> ccard.getCreditLimit()>50000;
		System.out.println("Is greater than 50000 :="+ checkLimit.test(card));
	}

	public static void primitivePredicate(double amount) {
		
		DoublePredicate checkLimit = creditLimit -> creditLimit>50000;
		System.out.println("Is greater than 50000 :="+ checkLimit.test(amount));
	}
	
	//Bi predicate with 2 argument
	public static void usingTwoArgsPredicate(CreditCard card,double expected) {
		//creating a lambda for BIpreicate and its method test
		BiPredicate<Double,Double> checkLimit = (limit,expt)->limit>expt;
		System.out.println( checkLimit.test(card.getCreditLimit(),expected));
	}
	
	
}
