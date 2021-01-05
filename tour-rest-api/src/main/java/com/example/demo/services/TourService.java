package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Tour;
import com.example.demo.respo.TourRepository;

@Service
public class TourService {
	
	@Autowired
	private TourRepository repo;
	
public List<Tour> findAll(){
		
		
		return this.repo.findAll();
		
		
	}


public Tour addTour(Tour entity){
	
	
	return this.repo.save(entity);
	
	
}


public Optional<Tour> findByTourId(Integer id) {
	
	return this.repo.findById(id);
}


public Tour deleteTour(Tour tour) {
	
	Tour tourDeleted = null;
	
	if(this.repo.existsById(tour.getTourId())) {
		
		this.repo.deleteById(tour.getTourId());
		
		tourDeleted = tour;
	}
	
	return tourDeleted;
	
}

public Tour updateTour(Tour tour) {
	
	return this.addTour(tour);
}

public List<Tour> findByTourName(String tourName) {
	
	return this.repo.findByTourName(tourName);
	
}

public List<Tour> greaterThanDuration(double duration){
	
	return this.repo.findByDurationGreaterThan(duration);
	
}
	
public int updateTour(int id,double revisedCost){
	return this.repo.updateTours(id,revisedCost);
	
}


public List<Tour> findToursByCost(double cost) {
	// TODO Auto-generated method stub
	return this.repo.findTourbyCost(cost);
}


	

}
