package com.example.demo.entity;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="blood_donor")
@Data
@NoArgsConstructor
@AllArgsConstructor

@IdClass(DonorCompositeKey.class)
public class BloodDonor {
	
	@Id
	private String donorName;
		
	private String donorAddress;
	
	private String donorCity;	
	
	@Id
	private String donorMobileNumber;
		
	private LocalDate donorDateofBirth;
	
	private String donorBloodGroup;
	
	private LocalDate lastDonateDate;

}
