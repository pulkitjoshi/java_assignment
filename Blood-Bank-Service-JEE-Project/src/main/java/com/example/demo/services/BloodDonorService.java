package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.DonorCompositeKey;
import com.example.demo.repository.BloodDonorRepository;

@Service
public class BloodDonorService {
	
	@Autowired
	private BloodDonorRepository donorRepo;
	
	public BloodDonor addBloodDonor(BloodDonor entity) {
		
		return this.donorRepo.save(entity);
		
	}
	
 
	public BloodDonor updateBloodDonor(BloodDonor entity) {
		
		return this.donorRepo.save(entity);
		
	}
	


	public boolean findDonorExistence(DonorCompositeKey id) {
		
		return this.donorRepo.existsById(id);
		
	}

	public Optional<BloodDonor> deleteBloodDonor(DonorCompositeKey id) {
	
		Optional<BloodDonor> deletedDonor=null;
		
		if(this.donorRepo.existsById(id))
		{
	     deletedDonor= this.donorRepo.findById(id);
	      this.donorRepo.deleteById(id);
		}
	     return deletedDonor;
		
		
	}
	
	
	public List<BloodDonor> findAll(){
		
		return this.donorRepo.findAll();
	}
	

	public List<BloodDonor> findByDonorCity(String donorCity){
		
		return this.donorRepo.findByDonorCity(donorCity);
	}
	

	public List<BloodDonor> findByDonorBloodGroup(String donorBloodGroup){
		
		return this.donorRepo.findByDonorBloodGroup(donorBloodGroup);
	}
	
	public List<BloodDonor> findDonorByEligibility(){
		
		return this.donorRepo.findDonorByEligibility();
	}
}
