package com.exercise;

public class BookService {
	
	public double Discount(Book b) {
		
		double discount=0.0;
		
		if(b.getPrice()>=500 && b.getPrice()<1000) {
			
			discount = 50.00;
			
		}
		
		else if (b.getPrice()>=1000) {
			
			discount = 150;
			
		}
		
		return discount;
		
		
	}
	
	
	public String Discount(String custtype) {
		
		String discount = "0%";
		
		if(custtype.equals("corparate")) {
			
			discount = "10%";
		}
		
		else if (custtype.equals("retail")) {
			
			discount = "2%";
		}
		
		return discount;
	}
	

}
