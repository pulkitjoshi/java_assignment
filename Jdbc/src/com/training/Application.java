package com.training;

import java.time.LocalDate;
import java.time.Month;

import com.training.daos.DoctorDaoImpl;
import com.training.entity.Doctor;
import com.training.ifaces.DataAccess;
import com.training.utils.DbConnectionUtil;
import java.sql.*;
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println(DbConnectionUtil.getMySqlConnection());
		
		try {
			Connection con = DbConnectionUtil.getMySqlConnection();
			DatabaseMetaData metaData = con.getMetaData();
			System.out.println("datbase metadata -class:="+metaData.getClass().getName());
			System.out.println("datbase metadata -class:="+metaData.getURL());
			System.out.println("database metadata -class:="+metaData.getDatabaseProductName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		int 	key =1;
	   DataAccess<Doctor> dao = new DoctorDaoImpl();
	   switch(key) {
	   
	   case 1:
		   Doctor doc = new Doctor(101,"Ramesh",8005529,"ENT",LocalDate.of(1970,Month.OCTOBER,12));
	   
		   int rowsAdded = dao.add(doc);
		   System.out.println("row Added:= "+rowsAdded);
		   break;
	   case 2:
		   dao.findAll().stream().forEach(System.out::println);
		   break;
	   case 3:
		   Doctor doc1 = new Doctor(102,"Aman",89005529,"Pede",LocalDate.of(1972,Month.OCTOBER,12));
		   Doctor doc2= new Doctor(103,"Rohit",84005529,"EYE",LocalDate.of(1974,Month.OCTOBER,12));
		   int[] result = dao.addInBatch(doc1,doc2);
		   for(int resp:result)
		   {
			   System.out.println(resp);
		   }
		   
	   case 4:
			DoctorDaoImpl dao2=(DoctorDaoImpl)dao;
			 dao2.usingTransaction();
		 default:
			 break;
	   
	   }
	}

}
