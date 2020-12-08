package com.example.demo.model;

public abstract class BankAccount {
	
	private String accountNumber;
	private String accountHolderName;
	private double currentBalance;
	public BankAccount(String accountNumber, String accountHolderName, double currentBalance) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.currentBalance = currentBalance;
	}
	
	public abstract double deposit(double amount);
	public abstract double withdraw(double amount);

	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}


}
