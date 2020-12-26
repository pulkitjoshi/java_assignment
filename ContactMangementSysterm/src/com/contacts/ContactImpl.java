package com.contacts;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.AttributedString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.training.ifaces.DataAccess;
import com.training.utils.DbConnectionUtil;

public class ContactImpl implements DataAccess<ContactInfo> {
	
	private Connection con;

	public ContactImpl() {
		super();
		this.con = DbConnectionUtil.getMySqlConnection();
	}
	
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
							// System.out.println("\nNumber Already Linked With "+contactName);
							 status = 1;
							 return(status);
						 }
							
				} catch (Exception e) {
					// TODO: handle exception
					e.getStackTrace();
					System.err.println("error while accessesing contact_number table");
				}
				
				
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
					System.out.println("error while accessesing contact_details table");
				}
			
			
		 	
		return rowsAdded2;
	}
	
	
	
	
	
	
	@Override
	public int addContactFromFile(File file) {
		
		int rowsAdded = 0;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			
			String line = reader.readLine();
			int lineNumber =0;
		   while(line != null){		
				lineNumber+=1;
				String[] info = line.split(",");
				
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
	
	
	
	

	@Override
	public int[] addInBatch(ContactInfo... t) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int update(String name, String updateAtt) {
		// TODO Auto-generated method stub
        
        Scanner sc = new Scanner(System.in);
		ResultSet check = null;
		int rowupdated=0;
		String updatesql=null;
		String updateval=null;
		
		String checkname=null;
		String namecheck="select * from contact_details where contactName=?";
		 
		
		//checking if contact available with ive name
		 try(PreparedStatement checkstmt = con.prepareStatement(namecheck)) 
			{
				checkstmt.setString(1, name.toUpperCase());
				
				check = checkstmt.executeQuery();
				while(check.next())
				{
					checkname = check.getString("contactName");
				}
					
				
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.getStackTrace();
				System.out.println("error while accessesing contact_details table");
			}	
			
			
		 //if number is available with given name then update in number table
			if(checkname!=null) {
				
				if(updateAtt.equals("contactNumber"))
				{
				
						updatesql ="UPDATE contact_number SET contactNumber= ? WHERE contactNumber = ? ";
						System.out.print("\nEnter the New SET contactNumber := ");
						updateval = sc.nextLine();
						
					
					try (PreparedStatement updateNumberStmt = con.prepareStatement(updatesql)){
						
						updateNumberStmt.setString(1,updateval);
						updateNumberStmt.setString(2,name);
						
						
						rowupdated =  updateNumberStmt.executeUpdate();
						
					} catch (Exception e) {
						// TODO: handle exception
						e.getStackTrace();
						System.err.println("error while accessesing contact_number table");
					}
					
					
					
				}
				
				//if you want to update details of contact 
				else if((updateAtt.equals("contactName")) ||(updateAtt.equals("address")) || (updateAtt.equals("picRef")) || (updateAtt.equals("dob")) ||(updateAtt.equals("email")||(updateAtt.equals("contactType"))))
				{
									
					updatesql ="UPDATE contact_details SET "+updateAtt+"= ? WHERE contactName = ?";
					System.out.print("\nEnter the New "+updateAtt+" := ");
					updateval = sc.nextLine();
					
				
					try(PreparedStatement updateDetailStmt = con.prepareStatement(updatesql)) {
						
						
						if(updateAtt.equals("dob"))
						{
								
							LocalDate localDate=null;
							try {
								
								localDate = LocalDate.parse(updateval);
							
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
								
								updateval=updateval.toUpperCase();
							}
							
							updateDetailStmt.setString(1,updateval);
							
						}
						
						updateDetailStmt.setString(2,name.toUpperCase());
										
						rowupdated =  updateDetailStmt.executeUpdate();
											
					} catch (Exception e) {
						// TODO: handle exception
						
						e.getStackTrace();
						System.out.println("error while accessesing contact_details table");

					}
									
				}
								
				}
			else
			{
				System.out.println(" No Contact Available with This Name");
				rowupdated = 404;
			}
			
		return rowupdated;
	}

	@Override
	public int remove(String name) {
		
		int rowsDeleted = 0;
		
		
		
		String sql = "DELETE FROM contact_details WHERE contactName = ?";
					
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
						
				pstmt.setString(1, name.toUpperCase());
						
				rowsDeleted =  pstmt.executeUpdate();
			} 
			catch (Exception e) {	
					e.printStackTrace();;
			}
					
		return rowsDeleted;
		
	}

	
	
	@Override
	public List<String> birthdayReport(int month,int option) {
				
		List<String> list=new ArrayList<String>(); 
		
		String birthdaySql = " select d.dob as BirthDay,d.contactName as Name,d.email as Email,n.contactNumber as MobileNumber from contact_details d,(SELECT t.contactName, GROUP_CONCAT(t.contactNumber ) contactNumber  FROM contact_number t GROUP BY t.contactName) n where d.contactName=n.contactName and month(d.dob)=? order by day(dob)";
		
		
		try(PreparedStatement birthdayStmt = con.prepareStatement(birthdaySql)) {
			
			birthdayStmt.setInt(1, month);
			 	
			ResultSet birthDayResult = birthdayStmt.executeQuery();
			//String heading = String.format("%22s %38s %38s %38s ","\033[1mBirthDay\033[0m","\033[1mName\033[0m","\033[1mEmail\033[0m","\033[1mContact Number\033[0m");
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
				
				writeToFIle(list,"Birthdaylist.txt");
			}
			
			return list;
				
	}
	
	
	@Override
	public List<String> contactListByGroup(int option) {
		
		List<String> list=new ArrayList<String>();
		
		
		Scanner sc = new Scanner(System.in);
		
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
			
			writeToFIle(list,"ContactList(Groupwise).txt");
		}
			
		return list;
			
	}
	
	
	
	@Override
	public List<String> contactListByGroupSize(int option) {
		// TODO Auto-generated method stub
		String countSql= "select contactType,count(*) as total from contact_details group by contactType order by total";
		String contactListSql = "select d.contactName,n.contactNumber,d.contactType from contact_details d,(SELECT t.contactName, GROUP_CONCAT(t.contactNumber ) contactNumber  FROM contact_number t   GROUP BY t.contactName) n,("+countSql+") as count where d.contactName=n.contactName and d.contactType=count.contactType  order by count.total";
		
		String fileHeader= null;
		List<String> list=new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		
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
			
			
			writeToFIle(list,"ContactList(Based on Group Size).txt");
		}
		
		return list;
	
	}
	
	
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
			
			
			writeToFIle(list,"ContactList(Name and Number).txt");
		}
		
		return list;
	
	}
	
	
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
			
			
			writeToFIle(list,"ContactList(Name and Email).txt");
		}
		
		return list;
		
	}
	
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
