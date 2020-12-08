package com.exercis;

public class Plumber implements Billable {
	
	private double year;
	
	

	public Plumber(double year) {
		super();
		this.year = year;
	}



	public double getYear() {
		return year;
	}



	public void setYear(double year) {
		this.year = year;
	}



	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		double salary=0.0;
		if(this.getYear()<1)
			salary = 7000;
		else if (this.getYear()<5)
			salary = 10000;
		else 
			salary= 12000;
		return salary;
	}

}
