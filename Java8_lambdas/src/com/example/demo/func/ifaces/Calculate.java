package com.example.demo.func.ifaces;

@FunctionalInterface
public interface Calculate<T,R> {
	
	R apply(T t);

}
