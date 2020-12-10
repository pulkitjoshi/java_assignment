package com.training.ifaces;
import java.util.*;
public interface DataAccess<T> {
	
	public boolean add(T t);
	public T findById(int id);
	public List<T> findAll();
	public List<T> sortList(String sortBy);
	

}
