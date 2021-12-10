package com.neosoft.poc.main.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.poc.main.feignService.FeignClientService;
import com.neosoft.poc.main.model.College;
import com.neosoft.poc.main.model.ResponseDemo;
import com.neosoft.poc.main.model.Student;
import com.neosoft.poc.main.repository.StudentRepo;
import com.neosoft.poc.main.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	FeignClientService feignClientService;
	
	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		Student stu=studentRepo.save(student);
		return stu;
	}

	@Override
	public List<Student> getStudent() {
		// TODO Auto-generated method stub
		
		List<Student> list=studentRepo.findAll();
		return list;
	}

	@Override
	public ResponseDemo getSingleStudentWithCollege(int id) {
		// TODO Auto-generated method stub
		
		Student stu=studentRepo.findById(id);
		System.out.println(stu);
		System.out.println(stu.getCollegeId());
		College clg=feignClientService.getSingleCollege(stu.getCollegeId());
		System.out.println(clg);
		ResponseDemo respDemo=new ResponseDemo();
		respDemo.setStudent(stu);
		respDemo.setCollege(clg);
		System.out.println(respDemo);
		return respDemo;
	}

	@Override
	public List<Student> studentFromCollegeId(int cid) {
		// TODO Auto-generated method stub
		List<Student> stuList=studentRepo.findByCollegeId(cid);
		return stuList;
	}

}
