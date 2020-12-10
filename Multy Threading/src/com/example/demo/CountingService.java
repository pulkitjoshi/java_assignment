package com.example.demo;

public class CountingService {



	public CountingService() {
		
		// TODO Auto-generated constructor stub
	}
	
	public void countNumbers(int countUPTo) {
		
		int sum = 0 ;
		for(int i=0;i<=countUPTo;i++)
		{
			sum=i+sum;
			}
		
	
	System.out.println(Thread.currentThread().getName());
	System.out.println(sum);}
}
