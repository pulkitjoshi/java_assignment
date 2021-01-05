package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Tour;
import com.example.demo.services.TourService;

@RestController
@RequestMapping(path = "/api/v1/")
public class TourController {

	
	@Autowired
	private TourService service;
	
	@GetMapping(path = "tours")
	public List<Tour> findAll(){		
		return this.service.findAll();
	}
	
	// send object in form of json object
	@PostMapping(path = "tours")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Tour addTour(@RequestBody Tour tour) {
		
		return this.service.addTour(tour);
	}
	
	
	@GetMapping(path = "tours/{id}")
	public Optional<Tour> findByTourId(@PathVariable("id") Integer id) {
		
		return this.service.findByTourId(id);
	}
	
	@GetMapping(path = "tours/searchByName/{name}")
	public List<Tour> findByName(@PathVariable("name") String tourName){
		
		return this.service.findByTourName(tourName);
	}
	
	@DeleteMapping(path = "tours")
	public ResponseEntity<?> deleteTour(@RequestBody Tour tour) {
		
		Tour deletedTour = this.service.deleteTour(tour);
		
		if(deletedTour == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(deletedTour);
		}
	}
	
	@PutMapping(path = "tours")
	@ResponseStatus(value = HttpStatus.OK)
	public Tour updateTour(@RequestBody Tour tour) {
		
		return this.service.updateTour(tour);
	}
	
	@GetMapping(path = "tours/duration/{days}")
	public List<Tour> findByDuration(@PathVariable("days") double duration){
		
		return this.service.greaterThanDuration(duration);
	}
	
	@GetMapping(path = "tours/cost/{inr}")
	public List<Tour> findByCost(@PathVariable("inr") double cost){
		
		return this.service.findToursByCost(cost);
	}

	@PutMapping(path = "tours/cost/{tourId}/{revised}")
	public ResponseEntity<String> updateCost(@PathVariable("tourId") int id,@PathVariable("revised") double revisedCost){
		
		String msg = "Rows updated := " + this.service.updateTour(id, revisedCost);
		
		return ResponseEntity.ok().body(msg);
	}
}
