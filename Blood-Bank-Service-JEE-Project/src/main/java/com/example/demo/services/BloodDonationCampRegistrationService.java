package com.example.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BloodDonationCampRegistration;
import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.CampRegistrationCompositeKey;
import com.example.demo.entity.CampInfo;
import com.example.demo.repository.BloodDonationCampRegistrationRepository;
import com.example.demo.repository.CampInfoRepository;

@Service
public class BloodDonationCampRegistrationService {

	@Autowired
	private BloodDonationCampRegistrationRepository registrationRepo;
	
	@Autowired
	private CampInfoRepository campRepo;
	
	
	public BloodDonationCampRegistration addBloodDonor(BloodDonationCampRegistration entity) {
		
		return this.registrationRepo.save(entity);
		
	}
	

	public BloodDonationCampRegistration updateBloodDonor(BloodDonationCampRegistration entity) {
		
		return this.registrationRepo.save(entity);
		
	}
	


	public Optional<BloodDonationCampRegistration> deleteBloodDonor(CampRegistrationCompositeKey id) {
	
		Optional<BloodDonationCampRegistration> deletedDonor=null;
		
		if(this.registrationRepo.existsById(id))
		{
	     deletedDonor= this.registrationRepo.findById(id);
	      this.registrationRepo.deleteById(id);
		}
	     return deletedDonor;
		
		
	}
	
	public List<BloodDonationCampRegistration> findByCampId(String campId){
		
		return this.registrationRepo.findByDonationCampId(campId);
	}
	
	
	/*private void deleteCampAllRegistration(String id) {
		
		this.registrationRepo.deleteByCampId(id);
		
		
		
		
	}*/

	public List<CampInfo> findAll(){
		
		return this.campRepo.findAll();
	}
	
	
	public List<CampInfo> findByCampLocation(String campLocation){
		
		return this.campRepo.findByCampLocation(campLocation);
	}	
	
	
	/*public Optional<CampInfo> deleteCampInfo(String id) {
		
		Optional<CampInfo> deletedDonor=null;
		
		if(this.campRepo.existsById(id))
		{
			deleteCampAllRegistration(id);
	     deletedDonor= this.campRepo.findById(id);
	       
	      this.campRepo.deleteById(id);
		}
	     return deletedDonor;
		
		
	}*/
	
	
	
	public List<String> findCampList(){
		
		return this.campRepo.findCampList();
	}
	
}
