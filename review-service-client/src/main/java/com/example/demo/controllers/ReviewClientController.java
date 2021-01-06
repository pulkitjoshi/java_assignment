package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ReviewClientController {

	@Autowired
	private RestTemplate template;
	
	
	@Autowired
	private LoadBalancerClient lbclient;
	
	@GetMapping(path="/client/reviews")
	public String getReviews() {
		
		
		System.out.println(lbclient.getClass().getName());
		
		ServiceInstance instance = lbclient.choose("REVIEW-SERVICE");
		
		String baseURL = instance.getUri().toString();
		
		String reqURL = baseURL+"/reviews";
				
		return this.template.getForObject(reqURL,String.class);
		
	}
}
