package com.springyboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springyboot.repos.UserRepo;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserRepo userRepo;
	
	public WebSecurityConfiguration(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	/**
	 * Disables Cross-Site Forgery Request protection.
	 * That's ok with the implementation of JWT authentication.
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
	}
	
	/**
	 * Future site of JWT Auth method
	 */
	
}
