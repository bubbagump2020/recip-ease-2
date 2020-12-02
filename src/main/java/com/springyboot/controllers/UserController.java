package com.springyboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springyboot.models.User;
import com.springyboot.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	private UserService us;
	
	@Autowired
	public UserController(UserService us) {
		super();
		this.us = us;
	}
	
	@PostMapping("/new")
	public ResponseEntity<String> save(@RequestBody User user){
		String pwHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		User persistentUser = new User(0, user.getEmail(), pwHash);
		if(us.save(persistentUser)) {
			return new ResponseEntity<>("User created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("User not created!", HttpStatus.CONFLICT);
		}
	}
}
