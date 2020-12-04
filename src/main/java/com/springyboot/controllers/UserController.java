package com.springyboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springyboot.models.User;
import com.springyboot.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	private UserService us;
	
	@Autowired
	public UserController(UserService us) {
		super();
		this.us = us;
	}
	
	@GetMapping("")
	public List<User> findAll(){
		return us.findAll();
	}
	
	@PostMapping("")
	public ResponseEntity<String> save(@RequestBody User user){
		String pwHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		User persistentUser = new User(0, user.getEmail(), user.getUsername(), pwHash);
		if(us.save(persistentUser)) {
			return new ResponseEntity<>("User created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("User not created!", HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/{email:.+}")
	public User findUserByEmail(@PathVariable String email) {
		System.out.println(email);
		return us.findUserbyEmail(email);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){
		final String USERNAME = user.getEmail();
		User currentUser = us.findUserbyEmail(USERNAME);
		if(BCrypt.checkpw(user.getPassword(), currentUser.getPassword())) {
			return new ResponseEntity<>(currentUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
