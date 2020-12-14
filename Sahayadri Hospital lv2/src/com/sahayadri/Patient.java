package com.sahayadri;

import java.time.LocalDate;

public class Patient {
	
	private String patientName;
	private int patientAge ;
	private String doctorName;
	private LocalDate date;
	public Patient(String patientName, int patientAge, String doctorName ,LocalDate date) {
		super();
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.doctorName = doctorName;
		this.date = date;
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
	
	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return patientName +"  "+ patientAge +"  "+ doctorName;
	}


	
	

}
