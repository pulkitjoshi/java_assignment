package com.training.ifaces;

import java.util.List;

public interface DataAccess<T> {
	
	public int add(T t);
	public int[] addInBatch(T ...t);
	
	
	public List<T> findAll();
	public T update(T existing, T update);
	public int remove(int id);
	
	public T findById(int id);

}
