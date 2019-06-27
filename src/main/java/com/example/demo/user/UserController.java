package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User findAll(@PathVariable int id){
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("Id=>"+id);
		}
		return user;
	}
}
