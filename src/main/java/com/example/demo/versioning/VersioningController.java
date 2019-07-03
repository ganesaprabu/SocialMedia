package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	/*
	 * URI Versioning starts
	 * */
	@GetMapping("/v1/persons")
	public PersonV1 getPersonV1URI() {
		return new PersonV1("Sachin Tendulkar"); 
	}
	
	@GetMapping("/v2/persons")
	public PersonV2 getPersonV2URI() {
		return new PersonV2(new Name("Sachin", "Tendulkar")); 
	}
	// URI Versioning ends #######################################
	
	/*
	 * Request Param Versioning starts
	 * */
	@GetMapping(value = "/persons", params = "version=1")
	public PersonV1 getPersonV1Param() {
		return new PersonV1("Sachin Tendulkar"); 
	}
	
	@GetMapping(value = "/persons", params = "version=2")
	public PersonV2 getPersonV2Param() {
		return new PersonV2(new Name("Sachin", "Tendulkar")); 
	}
	// Request Param Versioning ends #######################################
	
	/*
	 * Header Versioning starts
	 * */
	@GetMapping(value = "/persons", headers = "X-API-VERSION=1")
	public PersonV1 getPersonV1Headers() {
		return new PersonV1("Sachin Tendulkar"); 
	}
	
	@GetMapping(value = "/persons", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2Headers() {
		return new PersonV2(new Name("Sachin", "Tendulkar")); 
	}
	// Header Versioning ends #######################################
	
	/*
	 * Content Negotiation Versioning or produces starts
	 * */
	@GetMapping(value = "/persons", 
			produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonV1ContentNegotiation() {
		return new PersonV1("Sachin Tendulkar"); 
	}
	
	@GetMapping(value = "/persons", 
			produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonV2ContentNegotiation() {
		return new PersonV2(new Name("Sachin", "Tendulkar")); 
	}
	// Content Negotiation Versioning or produces ends #######################################
}
