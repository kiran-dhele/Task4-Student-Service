package com.neosoft.poc.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RunAs;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.web.reactive.server.MockServerClientHttpResponse;
import org.testng.annotations.BeforeClass;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.neosoft.poc.main.feignService.FeignClientService;
import com.neosoft.poc.main.model.College;
import com.neosoft.poc.main.model.ResponseDemo;
import com.neosoft.poc.main.model.Student;
import com.neosoft.poc.main.repository.StudentRepo;
import com.neosoft.poc.main.serviceImpl.StudentServiceImpl;

@SpringBootTest()
class StudentServiceApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	

	@MockBean
	StudentRepo  studentRepo;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@MockBean
	public FeignClientService feignClientService;
	
	@Autowired
	public FeignClientService feignClientWithoutMock;
	
//	@Autowired
//	WireMockServer wiremock=new WireMockServer(new WireMockConfiguration().port(0));

	
	@Test
	public void saveStudentTest()
	{
		Student stu=new Student();
		stu.setId(1001);
		stu.setFirstName("Pravin");
		stu.setLastName("Banger");
		stu.setMobileNo("9922310095");
		stu.setCollegeId(2001);
		
		when(studentRepo.save(stu)).thenReturn(stu);
		assertEquals(stu, studentServiceImpl.saveStudent(stu));
		assertNotNull(stu);
	}
	
	@Test
	public void getStudent()
	{
		Student stu=new Student();
		stu.setId(1001);
		stu.setFirstName("Pravin");
		stu.setLastName("Banger");
		stu.setMobileNo("9922310095");
		stu.setCollegeId(2001);
		
		Student stu1=new Student();
		stu1.setId(1002);
		stu1.setFirstName("Amar");
		stu1.setLastName("Banger");
		stu1.setMobileNo("778856980");
		stu1.setCollegeId(2002);
		
		List<Student> list=new ArrayList<Student>();
		list.add(stu);
		list.add(stu1);
		
		when(studentRepo.findAll()).thenReturn(list);
		assertEquals(list, studentServiceImpl.getStudent());
		assertNotNull(list);
	}
	
	@Test
	public void getSingleStudentWithCollege()
	{
		Student stu=new Student();
		stu.setId(1001);
		stu.setFirstName("Pravin");
		stu.setLastName("Banger");
		stu.setMobileNo("9922310095");
		stu.setCollegeId(2001);
		
		College clg=new College();
		clg.setId(2001);
		clg.setCollegeName("Sipna");
		clg.setCollegeCity("Amravati");
		
		ResponseDemo respDemo=new ResponseDemo();
		respDemo.setStudent(stu);
		respDemo.setCollege(clg);
		
	//	Optional<ResponseDemo> mockResp=Optional.of(respDemo);
		
		
		when(studentRepo.findById(stu.getId())).thenReturn(stu);
		when(feignClientService.getSingleCollege(stu.getCollegeId())).thenReturn(clg);
		
	//	assertThat(feignClientService.getSingleCollege(stu.getCollegeId())).isEqualTo(clg);
		
		assertEquals(clg, feignClientWithoutMock.getSingleCollege(stu.getCollegeId()));
		
	//	Mockito.when(feignClientService.getSingleCollege(stu.getCollegeId())).thenReturn(clg);
		
	//	assertThat(studentServiceImpl.getSingleStudentWithCollege(stu.getId())).isEqualTo(respDemo);
		assertEquals(studentServiceImpl.getSingleStudentWithCollege(stu.getId()),respDemo);
		System.out.println(respDemo);
		assertNotNull(respDemo);
	}
	
	@Test
	public void studentFromCollegeId()
	{
		List<Student> list=new ArrayList<Student>();
		Student s1=new Student();
		s1.setCollegeId(1001);
		Student s2=new Student();
		s2.setCollegeId(1002);
		list.add(s1);
		list.add(s2);
		
		when(studentRepo.findByCollegeId(s1.getCollegeId())).thenReturn(list);
		assertEquals(studentServiceImpl.studentFromCollegeId(s1.getCollegeId()), list);
		
	}

}
