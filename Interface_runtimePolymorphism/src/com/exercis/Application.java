package com.exercis;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SoftwareEngineer rakesh = new SoftwareEngineer("senior");
		
		System.out.println("Salary of Rakesh is := "+ rakesh.calculate());
		
		Plumber sattu = new Plumber(10);
		System.out.println("Salary of plumber sttu is :=" +sattu.calculate());

	}

}
