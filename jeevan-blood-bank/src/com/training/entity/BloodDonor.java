package com.training.entity;

import java.time.LocalDate;

public class BloodDonor {
	
	private String name;
	private int age;
	private String gender;
	private String bloodGroup;
	private String number;
	private String email;
	private LocalDate dob;
	public BloodDonor(String name, int age, String gender, String bloodGroup, String number, String email,
			LocalDate dob) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.number = number;
		this.email = email;
		this.dob = dob;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "BloodDonor [name=" + name + ", age=" + age + ", gender=" + gender + ", bloodGroup=" + bloodGroup
				+ ", number=" + number + ", email=" + email + ", dob=" + dob + "]";
	}
	

}
