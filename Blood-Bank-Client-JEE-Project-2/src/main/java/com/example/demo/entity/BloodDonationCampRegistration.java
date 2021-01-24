package com.example.demo.entity;




import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class BloodDonationCampRegistration {

	
	private String donorName;
	
	
	private String donorMobileNumber;
	
	
	private String donationCampId;
	
	
	/*@ManyToOne(cascade= CascadeType.ALL)
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "campId")
	@JsonSetter 
    private CampInfo donor;*/
	
}
