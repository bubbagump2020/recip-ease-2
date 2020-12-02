package com.springyboot.controllers;

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
@RequestMapping("/auth")
public class AuthenticationController {
	private UserService us;
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){
		User currentUser = us.findUserbyEmail(user.getEmail());
		if(BCrypt.checkpw(user.getPassword(), currentUser.getPassword())) {
			return new ResponseEntity<>(currentUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
