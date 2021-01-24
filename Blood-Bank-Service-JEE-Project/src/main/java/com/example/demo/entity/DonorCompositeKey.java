package com.example.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DonorCompositeKey implements Serializable{

	
	
	private static final long serialVersionUID = 3188274443686813641L;
	private String donorName;
	private String donorMobileNumber;
}
