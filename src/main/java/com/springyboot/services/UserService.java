package com.springyboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springyboot.models.User;
import com.springyboot.repos.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo ur;
	
	@Autowired
	public UserService(UserRepo ur) {
		super();
		this.ur = ur;
	}
	
	/**
	 * Returns a list of all users in the database
	 * @return List of all users
	 */
	public List<User> findAll(){
		return ur.findAll();
	}
	
	/**
	 * Find user by id
	 * @param id, the user's id
	 * @return the user that matches the param id
	 */
//	public User findUserbyId(Integer id) {
//		return ur.findUserById(id);
//	}
	
	/**
	 * find user by email
	 * @param email, the user's email
	 * @return the user that matches the param email
	 */
	public User findUserbyEmail(String email) {
		return ur.findByEmail(email);
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
	
	public User update(User user) {
		User findCurrentUser = ur.findByEmail(user.getEmail());
		return null;
	}
	
}
