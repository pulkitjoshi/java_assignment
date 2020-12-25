package com.training.ifaces;

import java.util.List;
import java.util.Map;

public interface DataAccess<T> {
	
	public int add(T t);
	public int[] addInBatch(T ...t);
	
	
	public List<T> findAll();
	
	public int remove(String name);
	
	public T findById(int id);
	public int update(String name, String string);
	public List<String> birthdayReport( int key,int option);
	
	public List<String> contactListByGroup(int option);
	public	List<String> contactListByGroupSize(int option);
	
	public List<String> ContactWithNameAndNumber(int option);
	public List<String> ContactWithNameAndEmail(int option);
	
	

}
