package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="camp_registration",schema = "targetSchemaName")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(CampRegistrationCompositeKey.class)
public class BloodDonationCampRegistration {

	@Id
	private String donorName;
	
	@Id
	private String donorMobileNumber;
	
	@Id
	private String donationCampId;
	
	
	/*@ManyToOne(cascade= CascadeType.ALL)
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "campId")
	@JsonSetter 
    private CampInfo donor;*/
	
}
