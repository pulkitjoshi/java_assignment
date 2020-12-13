package com.sahayadri;

import java.util.*;

public class PatientAppointment {
	
	
	private HashMap<String,ArrayList<Patient>> patientmap;

	public PatientAppointment() {
		super();
		this.patientmap = new HashMap<>();
	}
	
	
	public String fixAppointment(Patient patientInfo) {
		
		ArrayList<Patient> patientList =  patientmap.get(patientInfo.getDoctorName());
		

	    if(patientList == null) {
	    	
	    	patientList = new ArrayList<Patient>();
	    	
	    	patientList.add(patientInfo);
	    	
	    	this.patientmap.put(patientInfo.getDoctorName(), patientList);
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
	
	public List<Patient> printAppointment(String doctorName) {
			
			ArrayList<Patient> list = this.patientmap.get(doctorName);
		
			return list;
		}

	public String deleteAppointment(String patientName,String doctorName) {
		String message = "No Record Available";
		List<Patient> patient = this.patientmap.get(doctorName);
		
		try {
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
