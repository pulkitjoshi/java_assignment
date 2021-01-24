package com.example.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CampRegistrationCompositeKey implements Serializable {
	
	
	
	private static final long serialVersionUID = 2656397090654034217L;


	private String donorName;
	
	
	private String donorMobileNumber;
	
	
	private String donationCampId;

}
