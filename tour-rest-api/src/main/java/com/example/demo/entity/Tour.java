package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="lumen_tours")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
	
	@Id
	private int tourId;
	private String tourName;
	private double duration;
	private double cost;

}
