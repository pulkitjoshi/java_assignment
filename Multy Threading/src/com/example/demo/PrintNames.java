package com.example.demo;

public class PrintNames {
	
	public synchronized static void print(String str1,String str2) {
		
		System.out.println(str1);
		
		try {
			
			Thread.sleep(200);
			
		}catch(InterruptedException e) {
			e.printStackTrace();
			
		}
		System.out.println(str2);
		
	}

}
