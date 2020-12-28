package com.contacts.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.contacts.daos.ContactImpl;
import com.contacts.entity.ContactInfo;
import com.contacts.ifaces.DataAccess;
import com.contacts.utils.DbConnectionUtil;


class DatabaseTest {

	private Connection con;
	private DataAccess<ContactInfo> dao;
	
	
	@BeforeAll
	public void beforeTestStarted() {
		con = DbConnectionUtil.getMySqlConnection();
		
	}
	
	
	@BeforeEach
	public void beforeTestMethod() {
		
		 dao = new ContactImpl();
	}
	
	@Test
	@DisplayName(value="Test rerturn true If Database Connection is Established ")
	void test() throws SQLException {
		
		boolean Expected = con.isValid(30);
		boolean actual = true;
		assertEquals(Expected, actual);
		
	}
	
	 @Test
	 @DisplayName(value="Test rerturn Status=1 if Number is Already in Database and status=2 if Number is Already in Database ")
	    public void testFindStatus()
	    {
		 ContactInfo  newcontact = new ContactInfo("aman","8978654637");
		  int nameStatus = dao.checkAvailability(newcontact);

		  newcontact = new ContactInfo("aman","85746590987");
		  int numberStatus = dao.checkAvailability(newcontact);

		   assertEquals(2, nameStatus);
		   assertEquals(1, numberStatus);
	        return;
	    }
	 
	 
	 @Test
	 @DisplayName(value="Test rerturn ContactAdded=1 if Contact Not Present in Database")
	    public void testAddData()
	    {
		 ContactInfo  newcontact = new ContactInfo("udit","chennai","9867509863","udit.jpg",LocalDate.parse("1977-12-10"),"udit@gmail.com","relative");
		  int ContactAdded = dao.add(newcontact);

		  assertEquals(1, ContactAdded);
		   
	        return;
	    }
	 
	@Test
	 @DisplayName(value="Test rerturn ContactUpdated=1 if Contact Not Present in Database")
	    public void testUpdateData()
	    {
		 
		  //int ContactUpdate = dao.update("Aman", "9867598475", "57689485746");

		  //assertEquals(0, ContactUpdate);
		  
		  int ContactUpdate = dao.updateDetails("aman", "contactName", "mahi");
		  assertEquals(0, ContactUpdate);
		   
	        return;
	    }
	 
	 
	 @Test
	 @DisplayName(value="Test rerturn ContactUpdated=1 if Contact Not Present in Database")
	    public void testDeleteData()
	    {
		 ContactInfo  newcontact = new ContactInfo("tushar",null);
		
		  int ContactDeleted = dao.remove(newcontact);
		  assertEquals(0, ContactDeleted);
		   
		  
		  newcontact = new ContactInfo("Sahil","94857364785");
			
		  ContactDeleted = dao.remove(newcontact);
		  assertEquals(0, ContactDeleted);
	        return;
	    }
	 
	
	 
	 @AfterAll
	public void afterAllTestCompleted() throws SQLException {
			 con.close();
			
		}

}
