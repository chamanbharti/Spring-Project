package com.spring.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.model.User;
import com.spring.angular.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/fetch_users",headers="Accept=*/*",method=RequestMethod.GET)
	public @ResponseBody List<User> fetchUsers(){
		System.out.println("****fetchUsers()************");
		List<User> users=userService.fetchAllUsers();
		System.out.println("Users:"+users);
		
		return users;
	}
	
	@RequestMapping(value="/create_user",headers="Accept=*/*",method=RequestMethod.POST)
	public @ResponseBody int createUser(@RequestBody User user){
		System.out.println("****createUser()****");
		System.out.println("Incoming User:"+user);
		
		return userService.createUser(user);
	}
	@RequestMapping(value="/update_user",headers="Accept=*/*",method=RequestMethod.PUT)
	public @ResponseBody int updateUser(@RequestBody User user){
		
		System.out.println("****updateUser()****");
		System.out.println("Updating User:"+user);
		int updatedUser=0;
		try{
			updatedUser=userService.updateUser(user);
			
			if(updatedUser>0)
				System.out.println("user updated");
			else
				System.out.println("user not updated");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return updatedUser;
	}
	
	@RequestMapping(value="/delete_user/{id}",headers="Accept=*/*",method=RequestMethod.DELETE)
	public @ResponseBody int deleteUser(@PathVariable("id") int id){
		
		System.out.println("Fetching & Deleting User with id " + id);
		
		int userId=0;
		int deletedUser=0;
		userId = id;
		if(userId > 0){
			deletedUser=userService.deleteUser(userId);
		}
		
		if(deletedUser>0)
			System.out.println("user deleted");
		else
			System.out.println("user not deleted");
		
		return deletedUser;
	}
}
