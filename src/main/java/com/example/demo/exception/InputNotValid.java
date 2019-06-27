package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InputNotValid extends RuntimeException {

	public InputNotValid(String message) {
		super(message);
	}
	
}