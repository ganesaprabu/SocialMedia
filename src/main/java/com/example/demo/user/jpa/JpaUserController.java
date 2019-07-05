package com.example.demo.user.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.user.Post;
import com.example.demo.user.User;

@RestController
@RequestMapping("/jpa")
public class JpaUserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@GetMapping("/users")
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public User findOne(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id=>" + id);
		}
		return user.get();
	}

	@PostMapping("/users")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User newUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteOne(@PathVariable int id) {
		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException("Id-" + id);
		}
		postRepository.deleteAll(userRepository.findById(id).get().getPosts());
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	// Getting all the posts for a user
	@GetMapping("/users/{id}/posts")
	public List<Post> allPost(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id=>" + id);
		}
		return user.get().getPosts();
	}
	
	// Getting all the posts for a user
	@GetMapping("/users/{id}/posts/{postId}")
	public Post allPost(@PathVariable int id, @PathVariable int postId) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id=>" + id);
		}
		
		List<Post> posts = user.get().getPosts(); 
		Post postOutput = posts.stream()
			.filter(post -> post.getId() == postId)
			.findAny()
			.orElseThrow(() -> new PostNotFoundException("Post->"+postId));
		return postOutput;
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> savePost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id=>" + id);
		}
		
		post.setUser(user.get());
		
		Post newPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}