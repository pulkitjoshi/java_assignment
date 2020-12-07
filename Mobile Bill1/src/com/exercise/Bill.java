package com.exercise;

public class Bill {
	
	private String Name;
	private long MobileNumber;
	private String Plan;
	private int amount;
	public Bill(String name, long mobileNumber) {
		super();
		this.Name = name;
		this.MobileNumber = mobileNumber;
	}
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(String name,  long mobileNumber, String plan, int amount) {
		super();
		this.Name = name;
		this.MobileNumber = mobileNumber;
		this.Plan = plan;
		this.amount = amount;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public String getPlan() {
		return Plan;
	}
	public void setPlan(String plan) {
		Plan = plan;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	

}
