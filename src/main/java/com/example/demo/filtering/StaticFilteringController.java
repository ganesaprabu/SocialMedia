package com.example.demo.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticFilteringController {
	
	@GetMapping("/noPHI")
	public Member noPHI() {
		return new Member ("John", "Chennai", 123456789, 5000);
	}
}
