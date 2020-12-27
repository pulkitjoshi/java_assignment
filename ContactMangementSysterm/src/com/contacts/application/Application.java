package com.contacts.application;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.time.LocalDate;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import java.util.Scanner;

import com.contacts.daos.ContactImpl;
import com.contacts.entity.ContactInfo;
import com.contacts.ifaces.DataAccess;
import com.contacts.utils.DbConnectionUtil;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name=null;
		String address=null;
		String number=null;
		String imgRef=null;
		String dob=null;
		String email=null;
		String type=null;
		int genrateoption= 0;
		
	   List<String> list=new ArrayList<String>();
	   

		try {
			Connection con = DbConnectionUtil.getMySqlConnection();
			DatabaseMetaData metaData = con.getMetaData();
			System.out.println("datbase metadata -class:="+metaData.getClass().getName());
			System.out.println("datbase metadata -class:="+metaData.getURL());
			System.out.println("database metadata -class:="+metaData.getDatabaseProductName());
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean loop=true;
		Scanner sc = new Scanner(System.in);
		
		while(loop)
		{
			System.out.println("Please Choose your option\n1: Add New Contact\t2: Update Contact\t3: Delete Contact\t4: Add Contacts from File\t5: BirthDay Report\n"
								+ "6: Genrate Contact List Based on Group\t\t7: Genrate Contact List Based on Group Size\n"
								+ "8: Genrate Contact List with Name and Number\t9: Genrate Contact List with Name and Email\n"
								+ "10: Exit");
			System.out.print("Enter your Choice := ");
			int option = sc.nextInt();
						
		   DataAccess<ContactInfo> dao = new ContactImpl();
		   String fileName;
		   switch(option) {
		   
		   case 1:
			   
			   System.out.print("Enter Contact Details\nName := ");
			   name = sc.next()+sc.nextLine();
			   
			   System.out.print("Mobile Number := ");
			   number = sc.next()+sc.nextLine();
			   
			   ContactInfo  availabityinfo = new ContactInfo(name,number);
			   int status = dao.checkAvailability(availabityinfo);
			   int nameoption = 0;
			   if(status==1) {
				   
				   System.out.println("\nNumber Already Linked With "+availabityinfo.getName()+"\n");
			   }
			   
			   while(status==2) {
				   
				   System.out.print("\n"+availabityinfo.getName().toUpperCase()+" Already Exist \n 1) Add Contact For existing Name\n 2) Modify Name\nSelect Option := ");
					nameoption = sc.nextInt();
					
					if(nameoption==1)
					{
						 int rowsAdded = dao.numberToExistingContact(availabityinfo);
						 System.out.println("\nrow Added:= "+rowsAdded+"\n");
						 status= 1;
					}
					
					if(nameoption==2)
					{
						System.out.print("\nEnter New Name: ");
						String newName = sc.next()+ sc.nextLine();
						availabityinfo.setName(newName);
						status = dao.checkAvailability(availabityinfo);
					}
					
			   }
			  
			   if(status==0) {
					   System.out.print("Address := ");
					   address = sc.next()+sc.nextLine();
					  
					   System.out.print("imgRef := ");
					   imgRef = sc.next()+sc.nextLine();
					   System.out.print("Date of Brth := ");
					   
					   dob = sc.next()+sc.nextLine();
					   LocalDate localDate;
						try {
							localDate = LocalDate.parse(dob);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.getStackTrace();
							System.err.println("\nInvalid Date Type\n");
							continue;
						}
					   
					   System.out.print("Email := ");
					   email = sc.next()+sc.nextLine();
					   System.out.print("Contact Type := ");
					   type = sc.next()+sc.nextLine();
					   
					   
					   ContactInfo  newcontact = new ContactInfo(availabityinfo.getName(),address,number,imgRef,localDate,email,type);
				   
					   int rowsAdded = dao.add(newcontact);
					   System.out.println("\nrow Added:= "+rowsAdded+"\n");
			   }
			   break;
			   
		   case 2:
			   
			   System.out.print("\nEnter Name := ");
				name = sc.next()+sc.nextLine();
				availabityinfo = new ContactInfo(name,number);
				status = dao.checkAvailability(availabityinfo);
				if(status!=2)
				{
					System.out.println("Contact with this Name Not Aailable\n");
				}
				boolean interloop=true;
				String updateVal = null;
				while(interloop && status==2)
				{
					System.out.print("\nField You Want to Update\n1: Name   2: Address   3: Profile Pic   4: Date of Birth   \n5: Email   6: Contact Number   7:Contact Type   8:Done\nEnter Your Choice:= ");
				    String interchoice=sc.next();
				  
				    if(interchoice.equals("1"))
					{
				    	System.out.print("\nEnter the New Contact Name := ");
						updateVal = sc.next()+sc.nextLine();
						int rowupdate = dao.updateDetails(name, "contactName",updateVal);
						System.out.println("Rows Updated:= "+rowupdate);
						interloop=false;
							
						
					}
				    else if(interchoice.equals("2"))
					{
				    	System.out.print("\nEnter the New Contact Address := ");
						updateVal = sc.next()+sc.nextLine();
						int rowupdate = dao.updateDetails(name, "address",updateVal);
						System.out.println("Rows Updated:= "+rowupdate);
						
					}
					else if(interchoice.equals("3"))
					{
						System.out.print("\nEnter the New Pic Refrence := ");
						updateVal = sc.next()+sc.nextLine();
						int rowupdate = dao.updateDetails(name, "picRef",updateVal);
						System.out.println("Rows Updated:= "+rowupdate);
					}
					else if(interchoice.equals("4"))
					{
						System.out.print("\nEnter the New Date of Birth := ");
						updateVal = sc.next()+sc.nextLine();
						int rowupdate = dao.updateDetails(name, "dob",updateVal);
						System.out.println("Rows Updated:= "+rowupdate);
							
					}
					else if(interchoice.equals("5"))
					{
						System.out.print("\nEnter the New Contact Email := ");
						updateVal =sc.next()+ sc.nextLine();
						int rowupdate = dao.updateDetails(name, "email",updateVal);
						System.out.println("Rows Updated:= "+rowupdate);
					}
					else if(interchoice.equals("6"))
					{
						
						int toalNumberLinked = dao.totalNumberLinkedWithName(name);
						number=null;
						if(toalNumberLinked>1)
						{
							System.out.print("\nEnter the old Contact Number := ");
							number = sc.next()+sc.nextLine();
						}
						
						ContactInfo  updateInfo = new ContactInfo(name,number);
						System.out.print("\nEnter the New Contact Number := ");
						updateVal = sc.next()+sc.nextLine();
						int rowupdate = dao.updateNumber(updateInfo, updateVal,toalNumberLinked);
						System.out.println("Rows Updated:= "+rowupdate);
					}
					else if(interchoice.equals("7"))
					{
						System.out.print("\nEnter the New Contact Type := ");
						updateVal = sc.next()+sc.nextLine();
						int rowupdate = dao.updateDetails(name, "contactType",updateVal);
						System.out.println("Rows Updated:= "+rowupdate);
						
					}
					else 
					{
						interloop=false;
					}
				}

			   break;
			   
	
		   case 3:
			    number=null;
			    System.out.print("\n 1) Delete Contact\t2) Delete one Number From Contact\nSelect Option := ");
			    genrateoption = sc.nextInt();
				System.out.print("Enter Name := ");
				name = sc.next()+sc.nextLine();
				if(genrateoption==2)
				{
					System.out.print("Enter Number := ");
					number = sc.next()+sc.nextLine();
				}
				ContactInfo  deleteInfo = new ContactInfo(name,number);
				int rowsDeleted = dao.remove(deleteInfo);
				
				if(rowsDeleted==0)
				{
					System.out.println("No Contact With Above Details");
				}
				else
				{
					System.out.println("row Deleted:= "+rowsDeleted);
				}
			   break;
			   
		   
		   case 4:
			   System.out.print("Enter File Name := ");
			   fileName = sc.next()+sc.nextLine();
			   File myFile = new File(fileName);
			   int rowsAdded = dao.addContactFromFile(myFile);
			   System.out.println("row Added:= "+rowsAdded);
			   break;
		   
		  		   
		   case 5:
			   			
			   System.out.print("Enter BirthDay Month := ");
				int birthdayMonth = sc.nextInt();
				interloop = true;
				while(interloop)
				   {
						if(birthdayMonth<13 && birthdayMonth>0) {
							     genrateoption = getOption();
								list=dao.birthdayReport(birthdayMonth,genrateoption);
								 interloop =  ConsoleDisplay(list,genrateoption);
						}
						
						else {
							System.out.println("Enter valid Month Beteen 1-12");
						}
				   }
			   break;
			   
			   
		   case 6: 
			  
			   interloop = true;
			   while(interloop)
			   {
				   genrateoption = getOption();
				   list= dao.contactListByGroup(genrateoption);
				   					
					 interloop =  ConsoleDisplay(list,genrateoption);
				  
			   }
					
			   break;
			
		
		   case 7:
			   interloop = true;
			   while(interloop)
			   {
				   genrateoption = getOption();
				   list= dao.contactListByGroupSize(genrateoption);
				   					
					 interloop =  ConsoleDisplay(list,genrateoption);
				  
			   }
					
			   
			   break;
			   
			case 8:
				interloop = true;
				 while(interloop)
				   {
					 	 genrateoption = getOption();
					     list= dao.contactWithNameAndNumber(genrateoption);
					   					
						 interloop =  ConsoleDisplay(list,genrateoption);
					  
				   }
				
				
				break;
			
			case 9:
				interloop = true;
				 while(interloop)
				   {
						genrateoption = getOption();
					     list= dao.contactWithNameAndEmail(genrateoption);
					   					
						 interloop =  ConsoleDisplay(list,genrateoption);
					  
				   }
				
				
				break;
			
		   default:
				   sc.close();
				   loop=false;
				   
				   break;
				   
		   }

		}
	}
	
	
	
	private static int getOption()
	{
		 System.out.print("\n 1) Genrate Report in Console\t2) Genrate Report in File\t3) exit\nSelect Option := ");
			Scanner sc =  new Scanner(System.in);
			int selectedOption = sc .nextInt();
			
			return selectedOption;
			
		
	}
	
	

	
	
	private static  boolean ConsoleDisplay(List<String> list,int genrateoption) {
		boolean interloop = true;
		Iterator<String> iterator = list.iterator();
		if(genrateoption==1) {	
			while(iterator.hasNext()){
				String str = iterator.next();
				  if(str.contains("NAME")||str.contains("Contact"))
							System.out.print("\033[1m"+str+"\033[0m\n");
				  else
					  System.out.println(str+"\n");
			}
		}
		   
	   if(genrateoption>2)
	   {
		   interloop=false;
	   }
	   
	   return interloop;
	   
	}
	
}
