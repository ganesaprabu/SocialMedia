package com.example.demo.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel ("User Domain Model")
@Entity
@SequenceGenerator(name="USER_SEQ",
			sequenceName="USER_SEQ",
			allocationSize=1, 
			initialValue = 4)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USER_SEQ")
	private Integer id;
	@Size(min = 4, message = "Name should have 4 charecters")
	@ApiModelProperty("Name should have 4 charecters")
	private String name;
	@Past(message= "Should be Today or Past and not future date") @NotNull
	@ApiModelProperty("Should be Today or Past and not future date")
	private Date dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
	
	
	
}
