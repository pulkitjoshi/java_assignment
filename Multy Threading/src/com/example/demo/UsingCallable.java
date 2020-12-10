package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsingCallable {

	public static void getFromFuture(Future<Integer> task) {
		while(!task.isDone()) {
		System.out.println("Waiting");
		}
			try {
				System.out.println(task.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executor = Executors.newFixedThreadPool(3);

		
		Callable<Integer> upToTen = new SumNumber(10);
		Callable<Integer> upToHundred = new SumNumber(100);
		Callable<Integer> upToThosund = new SumNumber(1000);
		
		Future<Integer> task1 = executor.submit(upToTen);  
		Future<Integer> task2 = executor.submit(upToHundred);  
		Future<Integer> task3 = executor.submit(upToThosund); 
		
		getFromFuture(task1);
		getFromFuture(task2);
		getFromFuture(task3);
		
		
		
		
	}

}
