package com.example.demo;

public class ExecutorPrintService implements Runnable {
	private String str1;
	private String str2;

	public ExecutorPrintService(String str1, String str2) {
		super();
		this.str1 = str1;
		this.str2 = str2;
		
	}

	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		PrintNames.print(str1,str2);

	}

	
	

}
