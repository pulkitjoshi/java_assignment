package com.sahayadri;

import java.util.*;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatientAppointment appointment = new PatientAppointment();
		@SuppressWarnings("resource")
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Please Choose your option\n1: Fix New Appointment\n2: Check Appointment List\n3: Delete Appontment\n4: Exit");
			String option = sc.next();
			
			if(option.equals("1"))
			{
				
				
				System.out.println("Enter the name of patient");
				String patientName =sc.next()+  sc.nextLine();
				
				System.out.println("Enter the name of Doctor Name");
				String doctorName = sc.nextLine();
				
				System.out.println("Enter the name of Patient Age");
				int patientAge;
				try {
					patientAge = sc.nextInt();
					Patient patientInfo = new Patient(patientName,patientAge, doctorName);
					
					
					String message = appointment.fixAppointment(patientInfo);
					System.out.println(message);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("Enter Age in Integer");
					
					
				}
				
				
				
				
			}
			
			if(option.equals("2"))
			{
				System.out.println("Enter the name of Doctor Name");
				String doctor = sc.next()+ sc.nextLine();
				
				List<Patient> list = appointment.printAppointment(doctor);
				
				try {
					for(Patient p:list)
						System.out.println(p.getPatientName());
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					System.err.println("No Appointments Yet");
					
					
					
				}
				
				
				
			}
			
			if(option.equals("3"))
			{
				System.out.println("Enter the name of Patient Name");
				String patientName = sc.next()+ sc.nextLine();
				String doctorName = sc.next()+ sc.nextLine();
				String message = appointment.deleteAppointment(patientName, doctorName);
				System.err.println(message);
			}
			
			
			if(option.equals("4"))
			{
				
			  sc.close();
			  break;
			}
		}
		
		
	}

}
