package com.example.demo.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.UserNotFoundException;

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
	
	/*
	 * This is for the Internationalization (i18n). 
	 * Here the messages are being picked by based on the Locale from the request headers. 
	 *   
	 * */
	@GetMapping("/welcomeMessage")
	public String welcomeMessage(){
		return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
	}
	
}
