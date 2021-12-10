package com.neosoft.poc.main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Setter
@Getter
@ToString
public class College {
	
	private int id;
	private String collegeName;
	private String collegeCity;
}
