package com.exercis;

public class SoftwareEngineer implements Billable {
	
	private String level;
	
	

	public SoftwareEngineer(String level) {
		super();
		this.level = level;
	}



	public String getLevel() {
		return level;
	}



	public void setLevel(String level) {
		this.level = level;
	}



	
	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		double salary= 0.0;
		if(this.getLevel()=="senior")
			salary = 80000;
		else if(this.getLevel()=="junior")
			salary = 50000;
		else 
			salary = 30000;
		return salary;
	}

}
