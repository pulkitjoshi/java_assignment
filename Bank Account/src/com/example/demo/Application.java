package com.example.demo;
import com.example.demo.model.BankAccount;
import com.example.model.BusinessAccount;
import com.example.model.SavingAccount;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankAccount account = new SavingAccount("9833","Ramesh",1000.00,"nalini");
		//account.deposit(5000);
		account.deposit(1000000);
		
		//account.withdraw(1000);
		
		System.out.println("Current balance := "+account.getCurrentBalance());
		
		BankAccount bussinessAccount = new BusinessAccount("9833","Rohit",1000.00,"Rajeev");
		//account.deposit(5000);
		account.deposit(100000);
		
		account.withdraw(1000);
		System.out.println("Business Account balance := "+account.getCurrentBalance());

	}

}
