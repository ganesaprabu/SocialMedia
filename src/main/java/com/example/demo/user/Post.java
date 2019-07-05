package com.example.demo.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@SequenceGenerator(name="POST_SEQ",
				sequenceName="POST_SEQ",
				allocationSize=1, 
				initialValue = 4)
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="POST_SEQ")
	private Integer id;
	
	@Size(min = 4, message = "Post should not be more than 100 Charecters")
	@ApiModelProperty("Post should not be more than 100 Charecters")
	private String desc;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
}
