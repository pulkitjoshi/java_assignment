package com.example.demo;

import java.util.concurrent.Callable;

public class SumNumber implements Callable<Integer> {
	private int countUpTo;
	
	public SumNumber(int countUpTo) {
		
		// TODO Auto-generated constructor stub
	this.countUpTo = countUpTo;
	}
	
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int sum = 0 ;
		for(int i=0;i<=countUpTo;i++)
		{
			sum=i+sum;
			}
		
	
	System.out.println(Thread.currentThread().getName());
	
		return sum;
	}

	

}
