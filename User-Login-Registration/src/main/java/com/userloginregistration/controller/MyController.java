package com.userloginregistration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.userloginregistration.entity.User;
import com.userloginregistration.service.UserService;

@Controller
public class MyController {
	
	@Autowired
	private UserService userService;
	
	
	//http://localhost:8080/userlogin
	@GetMapping("/userlogin")
	public String userLogin(Model model) {
		model.addAttribute("user", new User());
		
		//error & info are used to show error or Info to the user
		model.addAttribute("error", null);
		model.addAttribute("info", null);
		return "login_page";
	}
	
	@PostMapping("/userlogin")
	public String userLoggingIn(@ModelAttribute("user") User user,Model model) {
		//error & info are used to show error or Info to the user
		model.addAttribute("info", null);
		Optional<User> existingUser = userService.getUserByName(user.getName());
		if(existingUser.isEmpty()) {
			model.addAttribute("error", "User not Found! Please Register Yourself...");
			return "login_page";
		} else {
			if(existingUser.get().getPassword().equalsIgnoreCase(user.getPassword())) {
				model.addAttribute("name", existingUser.get().getName());
				return "welcome";
			} else {
				model.addAttribute("error", "Password is Incorrect! Please Enter the correct password");
				return "login_page";
			}
		}
	}
	
	@GetMapping("/userregistration")
	public String userRegistration(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "registration";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user, Model model) {
		userService.addUser(user);
		model.addAttribute("info", "Registered Successfully. Please login with your credentials.");
		//error & info are used to show error or Info to the user
		model.addAttribute("error", null);
		return "login_page";
	}
	
//	@GetMapping("/userwelcome")
//	public String userWelcome(Model model) {
//		return "welcome";
//	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("info", "You have successfully logged out from the application.");
		//error & info are used to show error or Info to the user
		model.addAttribute("error", null);
		model.addAttribute("user", new User());
		return "login_page";
	}

}
