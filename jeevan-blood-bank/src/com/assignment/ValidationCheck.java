package com.assignment;

import com.assignment.UserInfo;

public class ValidationCheck {
	
	public static boolean isValid(UserInfo user) {
		boolean result=false;
		if((user.getUsername().equals("INDIA"))&&(user.getPassword().equals("INDIA")))
		{
			 result = true;
		}
		return result;
	} 

}
