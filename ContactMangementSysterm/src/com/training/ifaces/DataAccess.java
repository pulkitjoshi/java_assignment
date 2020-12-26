package com.training.ifaces;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.contacts.ContactInfo;

public interface DataAccess<T> {
	
	public int add(T t);
	public int[] addInBatch(T ...t);
	
	
	
	
	public int remove(String name);
	
	
	public int update(String name, String string);
	public List<String> birthdayReport( int key,int option);
	
	public List<String> contactListByGroup(int option);
	public	List<String> contactListByGroupSize(int option);
	
	public List<String> contactWithNameAndNumber(int option);
	public List<String> contactWithNameAndEmail(int option);
	
	public int checkAvailability(ContactInfo t);
	public int numberToExistingContact(ContactInfo t);
	public int addContactFromFile(File file);
	
	

}
