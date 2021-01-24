package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.DonorCompositeKey;


@Repository
public interface BloodDonorRepository extends JpaRepository<BloodDonor,DonorCompositeKey > {
	
	List<BloodDonor> findByDonorCity(String donorCity);
	
	List<BloodDonor> findByDonorBloodGroup(String donorBloodGroup);
	
	@Query(value = "select * from blood_donor where lastDonateDate<= DATE(NOW() - INTERVAL 6 MONTH) ",nativeQuery = true)
	List<BloodDonor> findDonorByEligibility();

}
