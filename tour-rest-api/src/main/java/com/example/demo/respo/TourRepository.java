package com.example.demo.respo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Tour;


@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

	List<Tour> findByTourName(String tourName);
	
	
	List<Tour> findByDurationGreaterThan(double duration);
	
	
	@Query(value = "select * from lumen_tours where cost>=:srchCondition",nativeQuery = true)
	List<Tour> findTourbyCost(@Param("srchCondition") double cost);
	
	
	@Query(value = "update lumen_tours set cost=:revised where tourId=:id",nativeQuery = true)
	@Modifying
	@Transactional
	int updateTours(@Param("id") int tourId,@Param("revised")  double cost);
	
	
	
}
