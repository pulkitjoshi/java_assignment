package com.sahayadri;

import java.util.*;
import java.time.LocalDate;


public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatientAppointment appointment = new PatientAppointment();
		@SuppressWarnings("resource")
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Please Choose your option\n1: Fix New Appointment\n2: Check Appointment List\n3: Delete Appontment\n4: Exit");
			System.out.print("Enter your Choice := ");
			String option = sc.next();
			
			if(option.equals("1"))
			{
				
				
				System.out.print("Enter the name of patient:= ");
				String patientName =sc.next()+  sc.nextLine();
				
				
				System.out.print("Enter the name of Patient Age:= ");
				int patientAge = sc.nextInt();
				
				System.out.print("Enter the name of Doctor Name:= ");
				String doctorName = sc.next()+sc.nextLine();
				
				System.out.print("Enter the Date of Appointment(yyyy-mm-dd):= ");
				String date = sc.next()+sc.nextLine();
				 LocalDate localDate;
				try {
					localDate = LocalDate.parse(date);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("\nInvalid Date Type\n");
					continue;
				}
				
			
					Patient patientInfo = new Patient(patientName,patientAge, doctorName,localDate);
					
					
					String message = appointment.fixAppointment(patientInfo, localDate);
					System.out.println("\n"+message+"\n");
					
				
					
					
				
				
				
				
				
			}
			
			if(option.equals("2"))
			{
				System.out.print("Enter the Date:= ");
				String date;
				date = sc.next()+ sc.nextLine();
				LocalDate localDate;
				try {
					localDate = LocalDate.parse(date);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.err.println("\nInvalid Date Type\n");
					continue;
				}
				
				
				System.out.print("Enter the name of Doctor Name:= ");
				String doctor = sc.next()+ sc.nextLine();
				
				List<Patient> list = appointment.printAppointment(doctor,localDate);
				
				try {
					for(Patient p:list)
						System.out.println(p.getPatientName());
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					System.err.println("\nNo Appointments Yet\n");
					
					
					
				}
				
				
				
			}
			
			if(option.equals("3"))
			{
				System.out.print("Enter the name of Patient Name:= ");
				String patientName = sc.next()+ sc.nextLine();
				System.out.print("Enter the name of Doctor Name:= ");
				String doctorName = sc.next()+ sc.nextLine();
				System.out.print("Enter the Date:= ");
				String date = sc.next()+ sc.nextLine();
				LocalDate localDate;
				try {
					localDate = LocalDate.parse(date);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("\nInvalid Date Type\n");
					continue;
				}
				String message = appointment.deleteAppointment(patientName, doctorName,localDate);
				System.err.println("\n"+message+"\n");
			}
			
			
			if(option.equals("4"))
			{
				
			  sc.close();
			  break;
			}
		}
		
		
	}

}
