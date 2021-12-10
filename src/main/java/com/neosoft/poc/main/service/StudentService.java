package com.neosoft.poc.main.service;

import java.util.List;

import com.neosoft.poc.main.model.ResponseDemo;
import com.neosoft.poc.main.model.Student;

public interface StudentService {

	public Student saveStudent(Student student);
	
	public List<Student> getStudent();

	public ResponseDemo getSingleStudentWithCollege(int id);

	public List<Student> studentFromCollegeId(int cid);
}
