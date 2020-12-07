package com.exercise;

public class Bill {
	
	private String name;
	private long mobileNumber;
	private String plan;
	private int amount;
	public Bill(String name, long mobileNumber) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
	}
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(String name,  long mobileNumber, String plan, int amount) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.plan = plan;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		mobileNumber = mobileNumber;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		plan = plan;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	

}
