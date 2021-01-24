package com.example.demo;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.BloodDonor;
import com.example.demo.repository.BloodDonorRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class BloodBankServiceJeeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodBankServiceJeeProjectApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner runner() {
		
		return new CommandLineRunner() {
			
			@Autowired
			BloodDonorRepository donorRepo;
						
			@Override
			public void run(String ...args) throws Exception{
				
				//donorRepo.save(new BloodDonor("Pulkit","Kothar Mohalla","Shahpura","8005529692",LocalDate.of(1997,Month.SEPTEMBER,13),"A+",null));
				
				
			}
		};

	}

}
