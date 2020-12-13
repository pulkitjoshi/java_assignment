package com.sahayadri;

public class Patient {
	
	
	private String patientName;
	private int patientAge ;
	private String doctorName;
	public Patient(String patientName, int patientAge, String doctorName) {
		super();
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.doctorName = doctorName;
	}
	
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return patientName +"  "+ patientAge +"  "+ doctorName;
	}


	
	

}
