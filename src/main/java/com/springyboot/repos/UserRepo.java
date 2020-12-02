package com.springyboot.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springyboot.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findUserById(Integer i);
	
	public User findUserByEmail(String s);
	
}
