package com.contacts;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.training.ifaces.DataAccess;
import com.training.utils.DbConnectionUtil;

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
	   
		
		ContactInfo newname = new ContactInfo();
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
		
		boolean loop=true;
		Scanner sc = new Scanner(System.in);
		
		while(loop)
		{
			System.out.println("Please Choose your option\n1: Add New Contact\t2: Update Contact\t3: Delete Contact\n4: BirthDay Report\t5: Genrate Contact List Based on Group\t6: Genrate Contact List Based on Group Size\n7: Exit");
			System.out.print("Enter your Choice := ");
			int option = sc.nextInt();
		
		
			
		   DataAccess<ContactInfo> dao = new ContactImpl();
		   switch(option) {
		   
		   case 1:
			   
			   System.out.print("Enter Contact Details\nName := ");
			   name = sc.next()+sc.nextLine();
			   System.out.print("Address := ");
			   address = sc.next()+sc.nextLine();
			   System.out.print("Mobile Number := ");
			   number = sc.next()+sc.nextLine();
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
			   
			   
			   ContactInfo  newcontact = new ContactInfo(name,address,number,imgRef,localDate,email,type);
		   
			   int rowsAdded = dao.add(newcontact);
			   System.out.println("\nrow Added:= "+rowsAdded+"\n");
			   break;
			   
		   case 2:
			   
			   System.out.print("\nEnter Name := ");
				name = sc.next()+sc.nextLine();
				boolean interloop=true;
				while(interloop)
				{
					System.out.print("\nField You Want to Update\n1: Name   2: Address   3: Profile Pic   4: Date of Birth   5: Email   \n6: Contact Number   7:Contact Type   8:Done\nEnter Your Choice:= ");
				    String interchoice=sc.next();
				  
				    if(interchoice.equals("1"))
					{
						
						int rowupdate = dao.update(name, "contactName");
						
						if(rowupdate!=404) {
							System.out.println("Rows Updated:= "+rowupdate);
						}
							
							interloop=false;
							
						
					}
				    else if(interchoice.equals("2"))
					{
						
						int rowupdate = dao.update(name, "address");
						interloop=conditionCheck(rowupdate);
						
					}
					else if(interchoice.equals("3"))
					{
						int rowupdate = dao.update(name, "picRef");
						interloop=conditionCheck(rowupdate);
					}
					else if(interchoice.equals("4"))
					{
						int rowupdate = dao.update(name, "dob");
						interloop=conditionCheck(rowupdate);
							
					}
					else if(interchoice.equals("5"))
					{
						int rowupdate = dao.update(name, "email");
						interloop=conditionCheck(rowupdate);
					}
					else if(interchoice.equals("6"))
					{
						int rowupdate = dao.update(name, "contactNumber");
						interloop=conditionCheck(rowupdate);
					}
					else if(interchoice.equals("7"))
					{
						int rowupdate = dao.update(name, "contactType");
						interloop=conditionCheck(rowupdate);
					}
					else 
					{
						interloop=false;
					}
				}

			   break;
			   
	
		   case 3:
			   
				System.out.print("Enter Name := ");
				name = sc.next()+sc.nextLine();
				int rowsDeleted = dao.remove(name);
				System.out.println("row Deleted:= "+rowsDeleted);
			   break;
			   
		   case 4:
			   			
			   System.out.print("Enter BirthDay Month := ");
				int birthdayMonth = sc.nextInt();
				interloop = true;
				while(interloop)
				   {
						System.out.print("\n 1) Genrate Report in Console\t2) Genrate Report in File\t3) exit\nSelect Option := ");
						genrateoption = sc.nextInt();
						if(birthdayMonth<13 && birthdayMonth>0) {
								list=dao.birthdayReport(birthdayMonth,genrateoption);
								 interloop =  ConsoleDisplay(list,genrateoption);
						}
						
						else {
							System.out.println("Enter valid Month Beteen 1-12");
						}
				   }
			   break;
			   
			   
		   case 5: 
			  
			   interloop = true;
			   while(interloop)
			   {
				   System.out.print("\n 1) Genrate Report in Console\t2) Genrate Report in File\t3) exit\nSelect Option := ");
					genrateoption = sc.nextInt();
				   list= dao.contactListByGroup(genrateoption);
				   					
					 interloop =  ConsoleDisplay(list,genrateoption);
				  
			   }
					
			   break;
			
		
		   case 6:
			   interloop = true;
			   while(interloop)
			   {
				   System.out.print("\n 1) Genrate Report in Console\t2) Genrate Report in File\t3) exit\nSelect Option := ");
					genrateoption = sc.nextInt();
				   list= dao.contactListByGroupSize(genrateoption);
				   					
					 interloop =  ConsoleDisplay(list,genrateoption);
				  
			   }
					
			   
			   break;
			   
			case 7:
				interloop = true;
				 while(interloop)
				   {
					   System.out.print("\n 1) Genrate Report in Console\t2) Genrate Report in File\t3) exit\nSelect Option := ");
						genrateoption = sc.nextInt();
					     list= dao.ContactWithNameAndNumber(genrateoption);
					   					
						 interloop =  ConsoleDisplay(list,genrateoption);
					  
				   }
				
				
				break;
			
			case 8:
				interloop = true;
				 while(interloop)
				   {
					   System.out.print("\n 1) Genrate Report in Console\t2) Genrate Report in File\t3) exit\nSelect Option := ");
						genrateoption = sc.nextInt();
					     list= dao.ContactWithNameAndEmail(genrateoption);
					   					
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
	
	
	private static boolean conditionCheck(int rowupdate) {
		boolean interloop = false;
		if(rowupdate!=404)
		{
			System.out.println("Rows Updated:= "+rowupdate);
			interloop=true;
		}
		return interloop;
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
