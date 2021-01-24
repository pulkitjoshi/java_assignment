package com.example.demo.entity;



import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Filter {

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//private LocalDate lastDonateDate;
	private String eligibilty="all";
	private String donorCity;	
	private String donorBloodGroup;
	
}
