package com.sahayadri;

import java.util.*;
import java.time.LocalDate;

public class PatientAppointment {
	
	
	private HashMap<LocalDate,HashMap<String,ArrayList<Patient>>> patientmap;

	public PatientAppointment() {
		super();
		this.patientmap =  new HashMap<LocalDate, HashMap<String,ArrayList<Patient>>>();
	}
	
	
	public String fixAppointment(Patient patientInfo,LocalDate date) {
		patientmap.put(date, new HashMap<String,ArrayList<Patient>>());
		ArrayList<Patient> patientList =  patientmap.get(date).get(patientInfo.getDoctorName());
		

	    if(patientList == null) {
	    	
	    	patientList = new ArrayList<Patient>();
	    	
	    	patientList.add(patientInfo);
	    	
	    	this.patientmap.get(date).put(patientInfo.getDoctorName(), patientList);
	    } 
	    

	    else {
	    	
	        if(!patientList.contains(patientInfo)) {
	        	patientList.add(patientInfo);
	        }
	        else {
	        	return "Not Fixed";
	        }
	    }
	    
	    return "Appointment Fixed";
	}
	
	public List<Patient> printAppointment(String doctorName,LocalDate date) {
			
			ArrayList<Patient> list = null;
			try {
				list = this.patientmap.get(date).get(doctorName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			    return list;
			}
		
			return list;
		}

	public String deleteAppointment(String patientName,String doctorName,LocalDate date) {
		String message = "No Record Available";

		
		try {
			List<Patient> patient = this.patientmap.get(date).get(doctorName);
			for(Patient p:patient)
			{
				
				if(p.getPatientName().equals(patientName))
				{
					
					patient.remove(p);
					
					message="Appointment Cancelled";
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return message;
		}
		
		return message;
	}
	

}
