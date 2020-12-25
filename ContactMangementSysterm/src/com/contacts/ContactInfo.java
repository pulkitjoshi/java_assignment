package com.contacts;

import java.time.LocalDate;

public class ContactInfo {
	
	

	private String name;
	private String address; 
	private String mobileNumber;
	private String imgReference;
	LocalDate dateOfBirth;
	private String email;
	private String contactType;
	public ContactInfo(String name, String address, String mobileNumber, String imgReference, LocalDate dateOfBirth,
			String email, String contactType) {
		super();
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.imgReference = imgReference;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.contactType = contactType;
	}
	
	public ContactInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getImgReference() {
		return imgReference;
	}
	public void setImgReference(String imgReference) {
		this.imgReference = imgReference;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	
	@Override
	public String toString() {
		return "ContactInfo [name=" + name + ", address=" + address + ", mobileNumber=" + mobileNumber
				+ ", imgReference=" + imgReference + ", dateOfBirth=" + dateOfBirth + ", email=" + email
				+ ", contactType=" + contactType + "]";
	}

}
