package com.example.demo.user;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/users")
	public List<User> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable int id){
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("Id=>"+id);
		}
		return user;
	}
	
	
	@GetMapping("/withLinks/users/{id}")
	public Resource<User> findOneWithLinks(@PathVariable int id){
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("Id=>"+id);
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder links = linkTo(methodOn(this.getClass()).findAll());
		resource.add(links.withRel("All-User"));
		return resource;
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<User> save(@Valid @RequestBody User user){
		User newUser = service.save(user);
		URI location = ServletUriComponentsBuilder
					.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteOne(@PathVariable int id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/proxy")
	public ResponseEntity<JsonNode> save(@RequestBody JsonNode jsonNode){
		Iterator keyValue = jsonNode.fields();
		while(keyValue.hasNext()) {
			System.out.println(keyValue.next());
		}
		System.out.println(jsonNode);
		return ResponseEntity.ok(jsonNode);
	}	
	
	@GetMapping("/welcomeMessage")
	public String welcomeMessage(@RequestHeader(name= "Accept-Language", required = false) Locale locale){
		System.out.println(locale);
		return messageSource.getMessage("welcome.message", null, Locale.US);
	}
	
}
