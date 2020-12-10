package com.example.demo;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingExecutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ExecutorService executor = Executors.newSingleThreadExecutor();
		
		ExecutorService executor = Executors.newFixedThreadPool(3);

		ExecutorPrintService task1 = new ExecutorPrintService("IDLY","Sambhar");
		ExecutorPrintService task2 = new ExecutorPrintService("PARANTHA","ACHAR");
		ExecutorPrintService task3 = new ExecutorPrintService("PIZZA","COKE");
		ExecutorPrintService task4 = new ExecutorPrintService("TEA","BISCUIT");
		
		executor.submit(task1);
		executor.submit(task2);
		executor.submit(task3);
		executor.submit(task4);
		
		executor.shutdown();
		
	}

}
