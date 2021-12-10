package com.neosoft.poc.main.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neosoft.poc.main.model.College;

import feign.Param;

@FeignClient(name="CollegeService", url="http://localhost:9092")
public interface FeignClientService {

	@RequestMapping(value = "/college/getSingleCollege/{id}", method = RequestMethod.GET)
	public College getSingleCollege(@PathVariable("id") int id);
	
}
