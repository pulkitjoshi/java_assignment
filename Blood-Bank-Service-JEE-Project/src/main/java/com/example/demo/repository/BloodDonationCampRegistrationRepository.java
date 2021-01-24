package com.example.demo.repository;



import java.util.List;

import javax.ws.rs.DELETE;

import org.hibernate.annotations.SQLDelete;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.demo.entity.BloodDonationCampRegistration;
import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.CampRegistrationCompositeKey;


@Repository
public interface BloodDonationCampRegistrationRepository extends JpaRepository<BloodDonationCampRegistration, CampRegistrationCompositeKey> {


	List<BloodDonationCampRegistration> findByDonationCampId(String donationCampId);
	
	//void DeleteByCampId(String campId);

	//long deleteByCampId(String campId);
	
	//@Query(value = "delete from donation_camp where campId=:srchCondition ",nativeQuery = true)
	//void DeleteByCampId(@Param("srchCondition") String campId);

}
