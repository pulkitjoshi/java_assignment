package com.example.demo.services;

public class UsingLambdas {
	
	public static void usingannoymousClass() {
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<5;i++) 
				{
					System.out.println(Thread.currentThread().getName() +i);
				}
			}
			
			
			
		});
		thread.start();
	}
		
		public static void usingLambda(){
			Thread thread = new Thread(()->{
				for(int i=0;i<5;i++) 
				{
					System.out.println(Thread.currentThread().getName() +i);
				}
				
			});
			thread.start();
		}
		
		public static void passingLambda(Runnable target) {
			
			Thread thread = new Thread(target);
			thread.start();
		}
	

}
