package com.example.demo.domains;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class Review {

	@Value("${server.port}")
	private String rating;

	public String getRating() {
		// TODO Auto-generated method stub
		return rating;
	}

	public void setRatig(String rating) {
		// TODO Auto-generated method stub
		this.rating = rating;
	}
}
