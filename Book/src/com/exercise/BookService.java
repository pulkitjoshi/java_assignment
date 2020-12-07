package com.exercise;

public class BookService {
	
	public double discount(Book mybook) {
		
		double discountPrice=0.0;
		
		if(mybook.getPrice()>=500 && mybook.getPrice()<1000) {
			
			discountPrice = 50.00;
			
		}
		
		else if (mybook.getPrice()>=1000) {
			
			discountPrice = 150;
			
		}
		
		return discountPrice;
		
		
	}
	
	
	public String discount(String custType) {
		
		String discountPercent = "0%";
		
		if(custType.equals("corparate")) {
			
			discountPercent = "10%";
		}
		
		else if (custType.equals("retail")) {
			
			discountPercent = "2%";
		}
		
		return discountPercent;
	}
	

}
