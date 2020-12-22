package com.training.daos;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;

import com.training.entity.*;
import com.training.ifaces.DataAccess;
import com.training.utils.DbConnectionUtil;

public class DoctorDaoImpl implements DataAccess<Doctor> {
	private static Connection con;
	

	public DoctorDaoImpl() {
		super();
		this.con = DbConnectionUtil.getMySqlConnection();
	}

	@Override
	public int add(Doctor t) {
		// TODO Auto-generated method stub
		String sql = "insert into lumen_doctor values(?,?,?,?,?)";
		
		int rowsAdded = 0;
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
		
			pstmt.setInt(1, t.getDoctorId());
			pstmt.setString(2, t.getDoctorName());
			pstmt.setLong(3, t.getMobileNumber());
			pstmt.setString(4, t.getSpecialization());
			
			pstmt.setDate(5,Date.valueOf(t.getDateOfBirth()));
			
			rowsAdded =  pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rowsAdded;
	}

	@Override
	public List<Doctor> findAll() {
		// TODO Auto-generated method stub
		
		String sql = "select *from lumen_doctor";
		
		List<Doctor> doctorList = new ArrayList<Doctor>();
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
		
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int doctorId=rs.getInt("doctorId");
				String doctorName= rs.getString("doctorName");
				int mobileNumber=rs.getInt("mobileNumber");
				String specialization=rs.getString("specialization");
				LocalDate dateOfBirth=rs.getDate("dateOfBirth").toLocalDate();
				Doctor doctor = new Doctor(doctorId,doctorName,mobileNumber,specialization,dateOfBirth);
				doctorList.add(doctor);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return doctorList;
	}

	@Override
	public Doctor update(Doctor existing, Doctor update) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public int remove(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Doctor findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] addInBatch(Doctor... list) {
		// TODO Auto-generated method stub
		String sql = "insert into lumen_doctor values(?,?,?,?,?)";
		int[] rows=null;
		
		
			try(PreparedStatement pstmt = con.prepareStatement(sql)) {
				con.setAutoCommit(false);
				for(Doctor eachdoctor:list) {
				pstmt.setInt(1, eachdoctor.getDoctorId());
				pstmt.setString(2, eachdoctor.getDoctorName());
				pstmt.setLong(3,eachdoctor.getMobileNumber());
				pstmt.setString(4, eachdoctor.getSpecialization());
				
				pstmt.setDate(5,Date.valueOf(eachdoctor.getDateOfBirth()));
				
				pstmt.addBatch(); 
				
			}
			rows=pstmt.executeBatch();	
			con.commit();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
		return rows;
	}
	
	
 public void usingTransaction() {
		 
		 String sql1 = "insert into lumen_doctor(doctorId,doctorName) values(?,?)";
		 String sql2 = "inseert into lumen_doctor(doctorId,doctorName) values(?,?)";
		 Savepoint p1=null;
		 try {
			
			  con.setAutoCommit(false);
			 PreparedStatement pstmt1 = con.prepareStatement(sql1);
			 PreparedStatement pstmt2 = con.prepareStatement(sql2);
			 
			  pstmt1.setInt(1, 201);
			  pstmt1.setString(2, "dummy");
			 
			 pstmt1.executeUpdate();
			 
			  p1 = con.setSavepoint("point1");
			  
			  pstmt2.setInt(1, 202);
			  pstmt2.setString(2, "munna");
			  
			  pstmt2.executeUpdate();
			  
			  con.commit();
			  
			  con.setAutoCommit(true);
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			try {
				con.rollback(p1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	 }
}
