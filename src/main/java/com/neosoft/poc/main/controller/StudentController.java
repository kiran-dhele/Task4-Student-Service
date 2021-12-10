package com.neosoft.poc.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.poc.main.model.ResponseDemo;
import com.neosoft.poc.main.model.Student;
import com.neosoft.poc.main.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

//this uri is used to save only student in database	
	@PostMapping("/saveStudent")
	public Student saveStudent(@RequestBody Student student)
	{
		Student stu=studentService.saveStudent(student);
		return stu;
	}

//this uri is use to get only all students from database	
	@GetMapping("/getStudent")
	public List<Student> getStudent()
	{
		List<Student> list=studentService.getStudent();
		return list;
	}

//this uri is use to get single student with his college by using student id.	
	@GetMapping("/getSingleStudent/{id}")
	public ResponseDemo getSingleStudentWithCollege(@PathVariable int id)
	{
		ResponseDemo resDemo=studentService.getSingleStudentWithCollege(id);
		return resDemo;
	}

//this uri is use to get students presents in particular college using college id	
	@GetMapping("/studentFromCollegeId/{cid}")
	public List<Student> studentFromCollegeId(@PathVariable("cid") int cid)
	{
		System.out.println("success");
		List<Student> list=studentService.studentFromCollegeId(cid);
		return list;
	}

}
