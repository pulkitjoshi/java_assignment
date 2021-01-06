package com.example.demo.domains;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	
	private double rating;

	private String reviewerName;
	
	private String location;
	
}
