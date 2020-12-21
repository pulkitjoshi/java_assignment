package com.example.demo.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.example.demo.model.Student;

public class StudentService {
	
	HashMap<String,List<Student>> studentList;
	
	public StudentService() {
		
		  studentList  =new HashMap<>();
		
		Student csc1 = new Student(100,"Ramesh",67);
		Student csc2 = new Student(200,"Rajesh",77);
		Student csc3 = new Student(210,"Suresh",87);
		Student csc4 = new Student(340,"Manish",97);
		
		List<Student> csc = Arrays.asList(csc1,csc2,csc3,csc4);
		
		Student ece1 = new Student(1200,"Ramesh",67);
		Student ece2 = new Student(2200,"Rajesh",77);
		Student ece3 = new Student(2210,"Suresh",87);
		Student ece4 = new Student(3240,"Manish",97);
		
		List<Student> ece = Arrays.asList(ece1,ece2,ece3,ece4);
		
		studentList.put("csc", csc);
		studentList.put("ece", ece);
		
		
	}
	
	public List<Student> getDetails(String branch) {
		
		return this.studentList.get(branch);
	}
	

}
