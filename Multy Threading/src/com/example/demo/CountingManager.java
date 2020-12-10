package com.example.demo;

public class CountingManager implements Runnable {

	private int countUpTo;
	private String threadName;
	private CountingService service;
	
	
	
	public CountingManager(int countUpTo, String threadName) {
		super();
		this.countUpTo = countUpTo;
		this.threadName = threadName;
		this.service = new CountingService();
		
		Thread thread = new Thread(this,this.threadName);
		thread.start();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.service.countNumbers(countUpTo);

	}

}
