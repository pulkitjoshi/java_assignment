package com.training.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.training.entity.BloodDonor;
import com.training.ifaces.DataAccess;


public class BloodDonorService implements DataAccess<BloodDonor> {

	Connection con =null;
	

	public BloodDonorService(Connection con) {
		super();
		this.con = con;
	}
	@Override
	public int add(BloodDonor donor) {
		
		String sql = "insert into blood_donor values(?,?,?,?,?,?,?)";
		
		int rowsAdded = 0;

		try(PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setString(1, donor.getName());
			pstmt.setInt(2, donor.getAge());
			pstmt.setString(3, donor.getGender());
			pstmt.setString(4,donor.getBloodGroup());
			pstmt.setString(5,donor.getNumber());
			pstmt.setString(6,donor.getEmail());
			pstmt.setDate(7,Date.valueOf(donor.getDob()));

			
			rowsAdded =  pstmt.executeUpdate();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return rowsAdded;
	}
	
	

	@Override
	public int[] addInBatch(BloodDonor... t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BloodDonor> findAll() {
		// TODO Auto-generated method stub
		String sql= "select * from blood_donor";
		System.out.println("connection ="+con);
		List<BloodDonor> donorList = new ArrayList<BloodDonor>();
		
		try(PreparedStatement pstmt= con.prepareStatement(sql)) {
			
			ResultSet rs= pstmt.executeQuery();
			
			
			while(rs.next()) {
			
				 
				  String name=rs.getString("name");
				  int age=rs.getInt("age");
				  String gender=rs.getString("gender");
			      String bloodGroup=rs.getString("bloodGroup");
				  String number=rs.getString("number");
				  String email=rs.getString("email");
				  LocalDate dob=rs.getDate("dob").toLocalDate();
				 
				 BloodDonor donor = new BloodDonor(name,age,gender,bloodGroup,number,email,dob);
				 donorList.add(donor);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return donorList;
	}

	@Override
	public BloodDonor update(BloodDonor existing, BloodDonor update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remove(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BloodDonor findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
