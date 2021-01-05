package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domains.Review;

@RestController
public class ReviewController {

	@Autowired
	private Review entity;
	
	
	@GetMapping(path="/reviews")
	public Review getReview() {
		
		entity.setRatig("Rating := "+entity.getRating());
		
		return entity;
	}
}
