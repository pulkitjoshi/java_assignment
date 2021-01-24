package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class BloodDonor {

	private String donorName;
	
	private String donorAddress;
	
	private String donorCity;	
	
	private String donorMobileNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate donorDateofBirth;
	
	private String donorBloodGroup;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastDonateDate;
	
}
