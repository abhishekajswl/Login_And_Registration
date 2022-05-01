package com.userloginregistration.service;

import java.util.Optional;

import com.userloginregistration.entity.User;


public interface UserService {
	
	public User addUser(User user);
	
	public Optional<User> getUserByName(String name);

}
