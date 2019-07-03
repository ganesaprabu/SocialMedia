package com.example.demo.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilteringController {

	@GetMapping("/noPHIdynamic")
	public MappingJacksonValue noPHIdynamic() {
		Provider provider = new Provider("John", "Chennai", 123456789, 5000);
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter 
						= SimpleBeanPropertyFilter.filterOutAllExcept("name", "address");
		
		FilterProvider filterProvider 
						= new SimpleFilterProvider().addFilter("noPHI", simpleBeanPropertyFilter);
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(provider);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
	
	@GetMapping("/includeRevenueDyna")
	public MappingJacksonValue includeRevenueDyna() {
		Provider provider = new Provider("John", "Chennai", 123456789, 5000);
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter 
						= SimpleBeanPropertyFilter.filterOutAllExcept("name", "address", "revenue");
		
		FilterProvider filterProvider 
						= new SimpleFilterProvider().addFilter("noPHI", simpleBeanPropertyFilter);
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(provider);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
	
	
}
