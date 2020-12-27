package com.contacts.utils;

import java.sql.*;
import java.util.Properties;


import java.io.*;
public class DbConnectionUtil {

	private static Connection con=null;
	
	public static Connection getMySqlConnection() {
		
		String fileName = "DbConnection.properties";
		
		try {
			InputStream inStream = DbConnectionUtil.class.getClassLoader().getResourceAsStream(fileName);
			Properties prop = new Properties();
			prop.load(inStream);
			
			con = DriverManager.getConnection(
					prop.getProperty("datasource.url"),
					prop.getProperty("datasource.username"),
					prop.getProperty("datasource.password")
					
					);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;	
		
		
	}
	
	
		
		
	}
	

