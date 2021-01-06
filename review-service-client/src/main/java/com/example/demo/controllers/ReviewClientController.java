package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class ReviewClientController {

	@Autowired
	private RestTemplate template;
	
	
	@Autowired
	private LoadBalancerClient lbclient;
	
	@GetMapping(path="/client/reviews/{name}")
	@HystrixCommand(fallbackMethod="getReviewsFallBack",
	                commandProperties=
	                @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="4000"))
	public String getReviews(@PathVariable("name") String name) {
		
		
		System.out.println(lbclient.getClass().getName());
		
		ServiceInstance instance = lbclient.choose("REVIEW-SERVICE");
		
		String baseURL = instance.getUri().toString();
		
		String reqURL = baseURL+"/reviews/"+name;
				
		return this.template.getForObject(reqURL,String.class);
		
	}
	
	public String getReviewsFallBack(String name) {
		
		return "{'rating':5.0,'reviewerName':'guest','location':'annoymous'}";
	}
}
