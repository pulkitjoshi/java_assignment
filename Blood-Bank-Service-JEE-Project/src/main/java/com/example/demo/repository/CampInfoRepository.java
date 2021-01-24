package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.CampInfo;

public interface CampInfoRepository extends JpaRepository<CampInfo, String> {

	List<CampInfo> findByCampLocation(String campLocation);

	@Query(value = "select campId from donation_camp ",nativeQuery = true)
	List<String> findCampList();
	
}
