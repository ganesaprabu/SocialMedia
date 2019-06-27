package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(100, "Adam"));
		users.add(new User(101, "Eve"));
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
}
