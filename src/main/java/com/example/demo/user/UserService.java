package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.InputNotValid;
import com.example.demo.exception.UserNotFoundException;

@Service
public class UserService {

	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id){
		for(User user : users) {
			if(user!=null && user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user){
		if(user == null || user.getName() == null 
				|| user.getName().trim().equals("")) {
			throw new InputNotValid("Input is not valid");
		}  
		user.setId(users.size()+1);
		users.add(user);
		return user;
	}
	
	public User delete(int id){
		Iterator<User> ite = users.iterator();
		User deletedUser = null;
		
		while(ite.hasNext()) {
			User user = ite.next();
			if(user.getId() == id) {
				deletedUser = user;
				ite.remove();
			}
		}
		if(deletedUser == null) {
			throw new UserNotFoundException("Id==>"+id);
		}
		return deletedUser;
	}
}
