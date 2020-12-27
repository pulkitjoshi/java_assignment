package com.contacts.ifaces;

import java.io.File;
import java.util.List;

import com.contacts.entity.ContactInfo;




public interface DataAccess<T> {
	
	public int add(T t);
	
	
	
	
	
	public int remove(T t);
	
	
	public int updateDetails(String name, String updateAtt,String updateval);
	public List<String> birthdayReport( int key,int option);
	
	public List<String> contactListByGroup(int option);
	public	List<String> contactListByGroupSize(int option);
	
	public List<String> contactWithNameAndNumber(int option);
	public List<String> contactWithNameAndEmail(int option);
	
	public int checkAvailability(T t);
	public int numberToExistingContact(T t);
	public int addContactFromFile(File file);

	public int updateNumber(ContactInfo t, String updateVal,int numberLinkedWithContact);

	int totalNumberLinkedWithName(String name);
	
	

}
