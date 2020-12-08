package com.example.model;

import com.example.demo.model.BankAccount;

public class SavingAccount extends BankAccount {
	
	private String nominee;
	
	public SavingAccount(String accountNumber,String accountHolderName,double currentBalance, String nominee) {
	
		super(accountNumber , accountHolderName , currentBalance);
		this.nominee = nominee;
	}

	@Override
	public double deposit(double amount) {
		// TODO Auto-generated method stub
		double newBalance = 0.0;
		
		if(amount>100000)
		{
			newBalance = this.getCurrentBalance();
			System.out.println("Account limit Exceed");
		}
		else
			
		  newBalance = this.getCurrentBalance() + amount;
		
		
		this.setCurrentBalance(newBalance);
		
		return newBalance;
		
	}

	@Override
	public double withdraw(double amount) {
		// TODO Auto-generated method stub
		double oldbalance = this.getCurrentBalance();
		double newBalance = oldbalance- amount;
		if(newBalance<5000)
		{
			 System.out.println("Account Balance is less Then limit");
		     newBalance = oldbalance;
	
		}
		this.setCurrentBalance(newBalance);
		return newBalance;
	}

}
