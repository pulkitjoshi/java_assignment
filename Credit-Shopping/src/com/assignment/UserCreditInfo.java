package com.assignment;

import java.util.HashMap;
import java.util.Map;

public class UserCreditInfo {

	private HashMap<String,Integer > userMap;

	public UserCreditInfo() {
		super();
		this.userMap = new HashMap<String,Integer>();
		this.userMap.put("AMAN", 4000);
		this.userMap.put("AKHIL", 2000);
		this.userMap.put("ROHIT", 5000);
		this.userMap.put("RAMIT", 10000);
		this.userMap.put("TUSHAR", 9000);
	}
	
	public Map<String, Integer> getUserMap() {
		return userMap;
	}

	
	
	
	
}
