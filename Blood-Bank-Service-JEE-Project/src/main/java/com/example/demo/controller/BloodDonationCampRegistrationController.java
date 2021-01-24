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

import com.example.demo.entity.BloodDonationCampRegistration;
import com.example.demo.entity.CampRegistrationCompositeKey;
import com.example.demo.entity.CampInfo;
import com.example.demo.services.BloodDonationCampRegistrationService;


@RestController
@RequestMapping(path = "/api/v1/")
public class BloodDonationCampRegistrationController {
	
	@Autowired
	private BloodDonationCampRegistrationService campService;
	
	@PostMapping(path = "campregister")
	public BloodDonationCampRegistration addDonor(@RequestBody BloodDonationCampRegistration donor) {		
		
		return 	this.campService.addBloodDonor(donor);
	}
	
	@PutMapping(path = "campregister")
	public BloodDonationCampRegistration updateBloodDonor(@RequestBody BloodDonationCampRegistration entity) {
		
		return this.campService.updateBloodDonor(entity);
	}
	
	@DeleteMapping(path = "campregister/{name}/{phoneNumber}/{campId}")
	public Optional<BloodDonationCampRegistration> deleteBloodDonor(@PathVariable("name") String name,
			@PathVariable("phoneNumber") String phoneNumber,@PathVariable("campid") String campId) {

		return this.campService.deleteBloodDonor(new CampRegistrationCompositeKey(name,phoneNumber,campId));
		
		//return this.campService.deleteBloodDonor(new CampRegistrationCompositeKey(name,phoneNumber));
	}
	
	/*@DeleteMapping(path = "campinfo/{campid}")
	public Optional<CampInfo> deleteCampInfo(@PathVariable("campid") String campid) {
		
		return this.campService.deleteCampInfo(campid);
	}*/
	
	
	@GetMapping(path = "showCampRegistration/{campId}")
	public List<BloodDonationCampRegistration> campRegistration(@PathVariable("campId") String campId){
		
		return this.campService.findByCampId(campId);
		
		
	}
	
	@GetMapping(path = "showCamp/")
	public List<CampInfo> findByCamp(){
		
		return this.campService.findAll();
		
		
	}
	
	@GetMapping(path = "showCamp/{campLocation}")
	public List<CampInfo> findByCampLocation(@PathVariable("campLocation") String campLocation){
		
		return this.campService.findByCampLocation(campLocation);
		
		
	}
	
	@GetMapping(path = "showCamp/camplist")
	public List<String> findCampList(){
		
		return this.campService.findCampList();
		
		
	}
	
	@GetMapping(path = "{campId}")
	public List<BloodDonationCampRegistration> findByCampId(@PathVariable("campId") String campId){
		
		return this.campService.findByCampId(campId);
	}
	
	
}
