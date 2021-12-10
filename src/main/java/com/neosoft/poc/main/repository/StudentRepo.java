package com.neosoft.poc.main.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.poc.main.model.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student, Integer>{

	public Student findById(int id);
	
	@Query("{collegeId:?0}")
	public List<Student> findByCollegeId(int cid);
}
