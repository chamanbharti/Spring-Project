package com.spring.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.model.User;

@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/fetch_users",headers="Accept=*/*",method=RequestMethod.GET)
	public @ResponseBody List<User> fetchUsers(){
		System.out.println("****fetchUsers()************");
		List<User> users=userRepository.findAll();
		System.out.println("Users:"+users);
		
		return users;
	}
	
}
