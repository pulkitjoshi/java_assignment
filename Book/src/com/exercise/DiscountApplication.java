package com.exercise;
import java.util.Scanner;

public class DiscountApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book math = new Book(101,"MATH","RD SHARMA",600);
		BookService discount = new BookService();
		
		System.out.println(discount.Discount(math));
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the customer type");
		String custtype = sc.next();
		System.out.println(discount.Discount(custtype));
		
	}

}
