package com.neosoft.poc.main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Document(collection = "student")
public class Student {
	
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private int collegeId;

}
