package com.springyboot.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springyboot.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	public Optional<User> findById(Integer i);
	
	public User findByEmail(String s);
	
	public User findByUsername(String s);
	
}
