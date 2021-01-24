package com.example.demo.entity;

import java.time.LocalDate;






import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampInfo {

	
	private String campId;
	private String campName;
	private String campLocation;
	private LocalDate campStartDate;
	private LocalDate campEndDate;
	
	
	
}
