package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.BloodDonationCampRegistration;
import com.example.demo.entity.BloodDonor;
import com.example.demo.entity.CampInfo;
import com.example.demo.entity.Filter;

@SpringBootApplication
@EnableDiscoveryClient
public class BloodBankClientJeeProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(BloodBankClientJeeProject2Application.class, args);
	}

	
	@Bean
	@LoadBalanced
	public RestTemplate template() {
		
		return new RestTemplate();
		
		
	}
	
	@Bean
	public ModelAndView modelAndView() {
		
		return new ModelAndView();
	}
	
	@Bean
	public BloodDonor bloodDonor() {
		return new BloodDonor();	
	}
	
	@Bean
    public Filter filter() {
		
		return new Filter();
	}
	
	@Bean
    public CampInfo campInfo() {
		
		return new CampInfo();
	}
	
	@Bean
    public BloodDonationCampRegistration bloodDonationCampRegistration() {
		
		return new BloodDonationCampRegistration();
	}
	
}
