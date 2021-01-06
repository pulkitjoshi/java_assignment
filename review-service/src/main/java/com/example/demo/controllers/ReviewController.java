package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domains.Review;

@RestController
public class ReviewController {

	@Autowired
	private Review entity;
	
	
	@GetMapping(path="/reviews/{name}")
	public Review getReview(@PathVariable("name") String reviewerName) throws InterruptedException {
		
		
		if(reviewerName.equalsIgnoreCase("ram")) {
			
			entity.setLocation("chennai");
			entity.setRating(4.5);
			entity.setReviewerName("ram");
			
		}
		else {
			 
			Thread.sleep(6000);
			entity.setLocation("tumkuru");
			entity.setRating(4.2);
			entity.setReviewerName("shyam");
			
		}
		
		return entity;
	}
}
