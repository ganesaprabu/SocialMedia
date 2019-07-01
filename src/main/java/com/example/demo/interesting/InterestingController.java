package com.example.demo.interesting;

import java.util.Iterator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class InterestingController {
	
	@PostMapping("/proxy")
	public ResponseEntity<JsonNode> save(@RequestBody JsonNode jsonNode){
		Iterator keyValue = jsonNode.fields();
		while(keyValue.hasNext()) {
			System.out.println(keyValue.next());
		}
		System.out.println(jsonNode);
		return ResponseEntity.ok(jsonNode);
	}
}
