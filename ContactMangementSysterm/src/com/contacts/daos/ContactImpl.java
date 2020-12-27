package com.contacts.daos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import com.contacts.entity.ContactInfo;
import com.contacts.ifaces.DataAccess;
import com.contacts.utils.DbConnectionUtil;

public class ContactImpl implements DataAccess<ContactInfo> {
	
	private Connection con;

	public ContactImpl() {
		super();
		this.con = DbConnectionUtil.getMySqlConnection();
	}
	
	
	
//Check if contact is already available for give Info and Return Status
//status = 0  =>  Contact With This Details Not Available
//status = 1  =>  Contact With This Number Already Available
//status = 2  =>  contact With This Name Already Available
   @Override
	public int checkAvailability(ContactInfo t) {
	   int status = 0;
	   String rowSql="select count(*) from contact_details";
	   String namecheck="select * from contact_details where contactName=?";
	   String numbercheck="select contactName from contact_number where contactNumber=?";
	   int row=0;
	   ResultSet check =null;
		
		try(PreparedStatement pstmt = con.prepareStatement(rowSql)) {
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) 
			{
			
				row = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.getStackTrace();
			System.err.println("error while accessesing contact_details table");
		}
			
		
		if(row!=0)
		{
				//checking if current number already added in contact list
				try(PreparedStatement checkNumStmt = con.prepareStatement(numbercheck)) {
					
					checkNumStmt.setString(1, t.getMobileNumber());
								
					ResultSet checknum = checkNumStmt.executeQuery();
					
					 while (checknum.next()) {
							 
							 String contactName = checknum.getString("contactName");
							 t.setName(contactName);
							
							 status = 1;
							 return(status);
						 }
							
				} catch (Exception e) {
					// TODO: handle exception
					e.getStackTrace();
					System.err.println("error while accessesing contact_number table");
				}
				
				//checking if current name already added in contact list
				try(PreparedStatement checkstmt = con.prepareStatement(namecheck)) 
				{
					checkstmt.setString(1, t.getName().toUpperCase());
					
					
					check = checkstmt.executeQuery();
					
					if(check.next())
					{
					
						status=2;
					}	
				}
				catch (SQLException e) {
					// TODO: handle exception
					e.getStackTrace();
					System.out.println("error while accessesing contact_details table");
				}
			}   
	   
		return status;
		// TODO Auto-generated method stub
	
	}
   
   

   
//function to link Contact Number whose contact details already present and return 1 if contact linked successfully
   @Override
   public int numberToExistingContact(ContactInfo t) {
   	// TODO Auto-generated method stub
	   int rowAdded = 0;
	   
		
			//adding new number for existing contact in contact_number table 
			String numberSql = "insert into contact_number values(?,?)";
			try( PreparedStatement nstmt = con.prepareStatement(numberSql)) {
						
				nstmt.setString(1, t.getName().toUpperCase());
				nstmt.setString(2, t.getMobileNumber());
							
				rowAdded=  nstmt.executeUpdate(); 
				
			} catch (Exception e) {
				// TODO: handle exception
				
				e.getStackTrace();
				System.err.println("error while accessesing contact_number table");
			}
			return rowAdded;
		   
   }

	
	
	
//function to add new contact in contact list with all information
	@Override
	public int add(ContactInfo t) {
		// TODO Auto-generated method stub
	 
		int rowsAdded2 = 0;
		
				//add data in contact_detail and contact number table for new contact entry
		
				String detailsql = "insert into contact_details values(?,?,?,?,?,?)";
				String numbersql = "insert into contact_number values(?,?)";
				
				try(PreparedStatement detailStmt = con.prepareStatement(detailsql) ) {
				
					try( PreparedStatement numberStmt = con.prepareStatement(numbersql)) {
						
						detailStmt.setString(1, t.getName().toUpperCase());
						detailStmt.setString(2, t.getAddress().toUpperCase());
						detailStmt.setString(3, t.getImgReference());
						detailStmt.setDate(4, Date.valueOf(t.getDateOfBirth()));
						detailStmt.setString(5, t.getEmail());
						detailStmt.setString(6, t.getContactType().toUpperCase());
												
						numberStmt.setString(1, t.getName().toUpperCase());
						numberStmt.setString(2, t.getMobileNumber());
					
						detailStmt.executeUpdate(); 
						rowsAdded2 = numberStmt.executeUpdate();
						
					} catch (Exception e) {
						// TODO: handle exception
						e.getStackTrace();
						System.err.println("error while accessesing contact_number table1");
					}
					
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.getStackTrace();
					System.err.println("error while accessesing contact_details table");
				}
				 	
		return rowsAdded2;
	}
	
	
	
		
//add contact from csv file
	@Override
	public int addContactFromFile(File file) {
		
		int rowsAdded = 0;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			
			String line = reader.readLine();
			int lineNumber =0;
		   while(line != null){		
				lineNumber+=1;
				String[] info = line.split(",");
				
				//checking if line has all the values in filed
				if(info.length==7)
				{
					String name = info[0];
					String address = info[1];
					String number = info[2];
					String picRef = info[3];
					
					LocalDate dob = Date.valueOf(info[4]).toLocalDate();
					
					String email =info[5];
					String contactType = info[6];
					
					ContactInfo newContact = null;
					
					
					try {
						newContact = new ContactInfo( name, address, number,picRef,  dob, email, contactType);
												
						int status = checkAvailability(newContact);
						
						if(status==1)
						{
							System.out.println("Number Already In List, Record Can't be Added of Line Number := "+lineNumber);
						}
						
						if(status==2)
						{
							System.out.println("Name Already In List, Record Can't be Added of Line Number := "+lineNumber);
						}
						
						if(status==0)
						{
							rowsAdded += add(newContact);
													
						}
											
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.err.println("Error while setting Values for:= "+name+" and LineNumber := "+lineNumber);
						e.printStackTrace();
					}
									
				}
							
				else {
					System.out.println("Invalid Data Entery Line := "+lineNumber);
				}
				
				line = reader.readLine();
				
			}
			
		}
		catch(Exception e) {		
			System.out.println(e.getMessage());
		}
			
		return rowsAdded;
	}
	
	
//update Number In Table	
	@Override
	public int updateNumber(ContactInfo t,String updateVal,int numberLinkedWithContact) {
		
		int rowUpdate = 0;
		
		
		//Counting number linked with particular contact
				
		String updatesql = null;
		
		
		if((t.getMobileNumber()==null)||(numberLinkedWithContact==1))
		{
			  updatesql ="UPDATE contact_number SET contactNumber= ? WHERE contactName = ? ";
			}
		else
		{
		  updatesql ="UPDATE contact_number SET contactNumber= ? WHERE contactNumber = ? ";
		}
		
		try (PreparedStatement updateNumberStmt = con.prepareStatement(updatesql)){
			
			updateNumberStmt.setString(1,updateVal);
			
			if((t.getMobileNumber()==null)||(numberLinkedWithContact==1))
			{
				updateNumberStmt.setString(2,t.getName());
			}
			else
			{
     			updateNumberStmt.setString(2,t.getMobileNumber());
			}
			
			
			rowUpdate =  updateNumberStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			System.err.println("error while accessesing contact_number table or Nuer Not Present in Table");
		}
		
		
		
		return rowUpdate;
	
	
	}
	
	
//function to update value for particular contact 
	@Override
	public int updateDetails(String name, String updateAtt,String updateVal) {
		// TODO Auto-generated method stub
        
		
		int rowupdated=0;
		String updatesql=null;
				
		
		
	    updatesql ="UPDATE contact_details SET "+updateAtt+"= ? WHERE contactName = ?";
									
		try(PreparedStatement updateDetailStmt = con.prepareStatement(updatesql)) {
						
						
			if(updateAtt.equals("dob"))
				{
					LocalDate localDate=null;
					try {
							localDate = LocalDate.parse(updateVal);
							
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getStackTrace();
						System.err.println("\nInvalid Date Type\n");
								
						return rowupdated;
					}
							
					updateDetailStmt.setDate(1, Date.valueOf(localDate));
				}
						
			else
			{
				if((updateAtt.equals("contactName")) ||updateAtt.equals("address")||(updateAtt.equals("contactType")))
				{
								
					updateVal=updateVal.toUpperCase();
				}
							
					updateDetailStmt.setString(1,updateVal);
							
			}
						
			updateDetailStmt.setString(2,name.toUpperCase());
										
			rowupdated =  updateDetailStmt.executeUpdate();
											
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
				System.err.println("error while accessesing contact_details table");

		}
									
			
		return rowupdated;
	}


	
	
//function to delete whole detail or particular number from contact
	@Override
	public int remove(ContactInfo deleteInfo) {
		
		int rowsDeleted = 0;
		int rowsHasTobeDeleted = 0;
		
		//Counting number linked with particular contact
		rowsHasTobeDeleted = totalNumberLinkedWithName(deleteInfo.getName());
				
		//if we either want to delete whole contact
		if((deleteInfo.getMobileNumber()==null)||(rowsHasTobeDeleted==1))
		{
			String deleteSql = "DELETE FROM contact_details WHERE contactName = ?";
						
			try(PreparedStatement deleteNameStmt = con.prepareStatement(deleteSql)) {
							
				deleteNameStmt.setString(1, deleteInfo.getName());
							
					deleteNameStmt.executeUpdate();
					rowsDeleted = rowsHasTobeDeleted; 
				} 
				catch (Exception e) {	
						e.printStackTrace();;
				}
		}
		
		
		//if we want to delete particular number for a contact
		if((deleteInfo.getMobileNumber()!=null)&&(rowsHasTobeDeleted>1)) {
			

			String deleteSql = "DELETE FROM contact_number WHERE contactNumber = ?";
						
			try(PreparedStatement deleteNameStmt = con.prepareStatement(deleteSql)) {
							
				deleteNameStmt.setString(1, deleteInfo.getMobileNumber().toUpperCase());
							
					rowsDeleted =  deleteNameStmt.executeUpdate();
				} 
				catch (Exception e) {	
						e.printStackTrace();;
				}
					
		}
					
		return rowsDeleted;
		
	}

	
	
	
//function to generate birthday report based on month shorted based on date
	@Override
	public List<String> birthdayReport(int month,int option) {
				
		List<String> list=new ArrayList<String>(); 
		
		String birthdaySql = " select d.dob as BirthDay,d.contactName as Name,d.email as Email,n.contactNumber as MobileNumber from contact_details d,(SELECT t.contactName, GROUP_CONCAT(t.contactNumber ) contactNumber  FROM contact_number t GROUP BY t.contactName) n where d.contactName=n.contactName and month(d.dob)=? order by day(dob)";
		
		
		try(PreparedStatement birthdayStmt = con.prepareStatement(birthdaySql)) {
			
			birthdayStmt.setInt(1, month);
			 	
			ResultSet birthDayResult = birthdayStmt.executeQuery();
			String fileHeader = String.format("%15s %30s %30s %30s ","BIRTHDAY","NAME","E-MAIL","CONTACT NUMBER");
			
			list.add(fileHeader+"\n");
		
			while(birthDayResult.next())
			{
				Date birthday = birthDayResult.getDate("BirthDay");
				String name  = birthDayResult.getString("Name");
				String email  = birthDayResult.getString("Email");
				String contactNumber  = birthDayResult.getString("MobileNumber");
				String info = String.format("%15s %30s %30s %30s ",birthday,name,email,contactNumber);
				list.add(info);
									
			}
			} 
			catch (Exception e) {	
					e.printStackTrace();;
			}
			
			if(option==2) {			
				
				writeToFIle(list,"E:\\java training\\ContactMangementSysterm\\Birthdaylist.txt");
			}
			
			return list;
				
	}
	
	
	

//function to generate contact report based on group 
	@Override
	public List<String> contactListByGroup(int option) {
		
		List<String> list=new ArrayList<String>();
		
		
		//Scanner sc = new Scanner(System.in);
		
		String[] group = {"RELATIVE","CLOSE FRIEND","PROFESSIONAL FRIEND"};
		
		String contactSql = "select d.contactName as Name,d.email as Email,n.contactNumber as MobileNumber from contact_details d,(SELECT t.contactName, GROUP_CONCAT(t.contactNumber ) contactNumber  FROM contact_number t GROUP BY t.contactName) n where d.contactName=n.contactName and d.contactType=?";
				
		
		for (String str : group) {
			
			try(PreparedStatement contactStmt = con.prepareStatement(contactSql)) {
				
				list.add(str+":- \n");
				contactStmt.setString(1, str);
				  	
				ResultSet contactResult = contactStmt.executeQuery();
				String fileHeader = String.format(" %30s %30s %30s ","NAME","E-MAIL","CONTACT NUMBER");
				
				list.add(fileHeader+"\n");
			
				while(contactResult.next())
				{
					String name  = contactResult.getString("Name");
					String email  = contactResult.getString("Email");
					String contactNumber  = contactResult.getString("MobileNumber");
					String info = String.format(" %30s %30s %30s ",name,email,contactNumber);
													
						list.add(info);
				}
				
				list.add("\n\n");
							
			} 
			catch (Exception e) {	
					e.printStackTrace();;
			}
			
			}
				
		if(option==2) {			
			
			writeToFIle(list,"E:\\java training\\ContactMangementSysterm\\ContactList(Groupwise).txt");
		}
			
		return list;
			
	}
	
	
	
	
//function to generate number of contact in each group and contact list in ascending order of group size
	@Override
	public List<String> contactListByGroupSize(int option) {
		// TODO Auto-generated method stub
		String countSql= "select contactType,count(*) as total from contact_details group by contactType order by total";
		String contactListSql = "select d.contactName,n.contactNumber,d.contactType from contact_details d,(SELECT t.contactName, GROUP_CONCAT(t.contactNumber ) contactNumber  FROM contact_number t   GROUP BY t.contactName) n,("+countSql+") as count where d.contactName=n.contactName and d.contactType=count.contactType  order by count.total";
		
		String fileHeader= null;
		List<String> list=new ArrayList<String>();
		
		//sql for number of contact in each group 
		try(PreparedStatement countStmt = con.prepareStatement(countSql)) {
			
			ResultSet groupSize = countStmt.executeQuery();
			fileHeader = String.format(" %30s %30s  ","CONTACT TYPE","TOTAL COUNT");
			
			list.add("Contact Groups Info:-\n\n"+fileHeader+"\n");

			while(groupSize.next())
			{
				String group  = groupSize.getString("contactType");				
				String totalCount  = groupSize.getString("total");
				String info = String.format(" %30s %30s  ",group,totalCount);
				
					list.add(info);
			}
							
		} 
		catch (Exception e) {	
				e.printStackTrace();;
		}
		
		//genrating contact list	
		try(PreparedStatement contactListStmt = con.prepareStatement(contactListSql)) {
			
			ResultSet contactList = contactListStmt.executeQuery();
			
			fileHeader = String.format(" %30s %30s %30s ","NAME","CONTACT NUMBER","CONTACT TYPE");
			
			list.add("\n\n\n\nContact List:-\n\n"+fileHeader+"\n");

			while(contactList.next())
			{
				
				String name  = contactList.getString("contactName");				
				String contactNumber  = contactList.getString("contactNumber");
				String contactType  = contactList.getString("contactType");
				String info = String.format(" %30s %30s %30s ",name,contactNumber,contactType);
				
					list.add(info);
				
			}
							
		} 
		catch (Exception e) {	
				e.printStackTrace();;
		}
		
	
		if(option==2) {			
			
			
			writeToFIle(list,"E:\\java training\\ContactMangementSysterm\\ContactList(Based on Group Size).txt");
		}
		
		return list;
	
	}
	
	
	
	
//function to generate contact list name and number
	@Override
	public List<String> contactWithNameAndNumber(int option){
		
		Map<String, String> contactMap = new HashMap<>();
		List<ContactInfo> contactFullDetails = contactFullInfo();
		List<String> list=new ArrayList<String>();
		
		String fileHeader = String.format(" %30s %30s  ","NAME","CONTACT NUMBER");
		list.add(fileHeader+"\n");
		
		
		contactMap =  contactFullDetails.stream().collect(Collectors.toMap(ContactInfo::getName,ContactInfo::getMobileNumber));
		
		contactMap.entrySet().forEach(entry->{
			String name = entry.getKey();
			String contactNumber = entry.getValue();
			String info = String.format(" %30s %30s  ",name,contactNumber);
			list.add(info);
		 });
		
		if(option==2) {			
			
			
			writeToFIle(list,"E:\\java training\\ContactMangementSysterm\\ContactList(Name and Number).txt");
		}
		
		return list;
	
	}
	
	
	
	
//function to generate contact list name and email
	@Override
   public List<String> contactWithNameAndEmail(int option){
		
		Map<String, String> contactMap = new HashMap<>();
		List<ContactInfo> contactFullDetails = contactFullInfo();
		List<String> list=new ArrayList<String>();
		
		String fileHeader = String.format(" %30s %30s  ","NAME","Email");
		list.add(fileHeader+"\n");
		
		
		contactMap =  contactFullDetails.stream().collect(Collectors.toMap(ContactInfo::getName,ContactInfo::getEmail));
		
		contactMap.entrySet().forEach(entry->{
			String name = entry.getKey();
			String email = entry.getValue();
			String info = String.format(" %30s %30s  ",name,email);
			list.add(info);
		 });
		
		if(option==2) {			
			
			
			writeToFIle(list,"E:\\java training\\ContactMangementSysterm\\ContactList(Name and Email).txt");
		}
		
		return list;
		
	}

	
	
//function to generate count number linked with a name
	
	@Override
	public int totalNumberLinkedWithName(String name){
		
		int total=0;
		String countSql="select count(*) from contact_number where contactName=?";
		try(PreparedStatement pstmt = con.prepareStatement(countSql)) {
			
			pstmt.setString(1, name);
					
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) 
			{
			
				total = rs.getInt("count(*)");
			}
		} 
		catch (Exception e) {	
				e.printStackTrace();
		}
		
		return total;
		
	}
	
	
	
//function to generate report in file
	private void writeToFIle(List<String> list,String fileName) {
		// TODO Auto-generated method stub
		
		 try {
		      File myObj = new File(fileName);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      }
		      
		      FileWriter myWriter = new FileWriter(fileName);
	      
		      Iterator<String> iterator = list.iterator();
				while(iterator.hasNext()){
								myWriter.write(iterator.next()+"\n");
				}
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		    } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	

	
	
//function to retrieve all data from file and store in list
	private List<ContactInfo> contactFullInfo(){
		
		List<ContactInfo> contactList = new ArrayList<>();
		
		String contactSql = "select d.contactName,d.address,d.picRef,d.contactType,d.dob,d.email,n.contactNumber from contact_details d,(SELECT t.contactName, GROUP_CONCAT(t.contactNumber ) contactNumber  FROM contact_number t GROUP BY t.contactName) n where d.contactName=n.contactName";

		try(PreparedStatement contactStmt = con.prepareStatement(contactSql)){
				
				ResultSet contactdata = contactStmt.executeQuery();
				
				while(contactdata.next()) {
					
					String contactName = contactdata.getString("contactName");
					String address = contactdata.getString("address");
					String contactNumber = contactdata.getString("contactNumber");
					String picRef= contactdata.getString("picRef");
					LocalDate dob = contactdata.getDate("dob").toLocalDate();
					String email = contactdata.getString("email");
					String contactType = contactdata.getString("contactType");
					
					ContactInfo contact = new ContactInfo(contactName, address, contactNumber,picRef,dob, email, contactType);
					
					contactList.add(contact);
				}
				
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		
		return contactList;
	}

	

}
