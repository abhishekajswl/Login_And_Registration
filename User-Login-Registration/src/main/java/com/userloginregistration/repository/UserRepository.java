package com.userloginregistration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userloginregistration.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByName(String name);

}
