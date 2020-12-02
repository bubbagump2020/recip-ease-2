package com.springyboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springyboot.models.User;
import com.springyboot.repos.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo ur;
	
	/**
	 * Find user by id
	 * @param id, the user's id
	 * @return the user that matches the param id
	 */
	public User findUserbyId(Integer id) {
		return ur.findUserById(id);
	}
	
	public User findUserbyEmail(String email) {
		return ur.findUserByEmail(email);
	}
	
	/**
	 * Saves a user to the database
	 * @param user
	 * @return true if success, false if failure
	 */
	public boolean save(User user) {
		if(user == null) {
			return false;
		}
		try {
			ur.save(user);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}
	
	
	
}
