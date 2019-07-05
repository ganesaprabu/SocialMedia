package com.example.demo.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Post;
import com.example.demo.user.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	
}
