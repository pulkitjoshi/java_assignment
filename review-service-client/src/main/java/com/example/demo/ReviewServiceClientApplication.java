package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class ReviewServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceClientApplication.class, args);
	}
	
	
  @Bean
  public RestTemplate template() {
	  
	  return new RestTemplate();
  }

}
