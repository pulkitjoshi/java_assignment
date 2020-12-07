package com.exercise;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bill aman = new Bill("AMAN",74892893,"ABCD",300);
		System.out.println(aman.getName());
		System.out.println(aman.getMobileNumber());
		System.out.println(aman.getPlan());
		System.out.println(aman.getAmount());
		
		Bill akhil = new Bill("AKHIL",738462537);
		System.out.println(akhil.getName());
		System.out.println(akhil.getMobileNumber());
		
		
	}

}
