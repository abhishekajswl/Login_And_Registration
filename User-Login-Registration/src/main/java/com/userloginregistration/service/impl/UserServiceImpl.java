package com.userloginregistration.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userloginregistration.entity.User;
import com.userloginregistration.repository.UserRepository;
import com.userloginregistration.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserByName(String name) {
		return userRepository.findUserByName(name);
	}

}
