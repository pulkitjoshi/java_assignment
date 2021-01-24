package com.example.demo.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="donation_camp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampInfo {

	@Id
	private String campId;
	private String campName;
	private String campLocation;
	private LocalDate campStartDate;
	private LocalDate campEndDate;
	
	
	/*@OneToMany(mappedBy="donor")
	 @JsonIgnore
    private Set<BloodDonationCampRegistration> bloodDonationCampRegistration;*/
	
}
