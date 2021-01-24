package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.DonorCompositeKey;
import com.example.demo.services.BloodDonorService;

@RestController
@RequestMapping(path = "/api/v1/")
public class BloodDonorController {

	@Autowired
	private BloodDonorService donorService;
	
	
	
	@PostMapping(path = "donor")
	public BloodDonor addDonor(@RequestBody BloodDonor donor) {		
		
		return 	this.donorService.addBloodDonor(donor);
	}
	
	@GetMapping(path = "donorExist/{name}/{phoneNumber}")
	public boolean findDonorExistence(@PathVariable("name") String name,
			@PathVariable("phoneNumber") String phoneNumber) {
		
		return this.donorService.findDonorExistence(new DonorCompositeKey(name, phoneNumber));
	}
	
	
	@PutMapping(path = "updatedonor")
	public BloodDonor updateBloodDonor(@RequestBody BloodDonor donor) {		
		
		return 	this.donorService.updateBloodDonor(donor);
	}
	
	@DeleteMapping(path = "donor/{name}/{phoneNumber}")
	public Optional<BloodDonor> deleteDonor(@PathVariable("name") String name,
			@PathVariable("phoneNumber") String phoneNumber) {

		
		return this.donorService.deleteBloodDonor(new DonorCompositeKey(name, phoneNumber));
	}
	
	
	
	@GetMapping(path = "showDonor/")
	public List<BloodDonor> showDonor(){
		
		return this.donorService.findAll();
	}
	
	@GetMapping(path= "showDonor/{donorCity}")
	public List<BloodDonor> findByDonorCity(@PathVariable("donorCity") String donorCity){
		
		return this.donorService.findByDonorCity(donorCity);
	}
	
	@GetMapping(path= "showDonorByGroup/{donorBloodGroup}")
	public List<BloodDonor> findByDonorBloodGroup(@PathVariable("donorBloodGroup") String donorBloodGroup){
		
		return this.donorService.findByDonorBloodGroup(donorBloodGroup);
	}
	

	@GetMapping(path= "showDonorByEligibility/")
	public List<BloodDonor> findDonorByEligibility(){
		
		return this.donorService.findDonorByEligibility();
	}
	
	
}
