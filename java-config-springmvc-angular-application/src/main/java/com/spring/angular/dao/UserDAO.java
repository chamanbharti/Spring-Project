package com.spring.angular.dao;

import java.util.List;

import com.spring.angular.model.User;

public interface UserDAO {
	
	int createUser(User user);
	
	List<User> fetchAllUsers();
	
	int updateUser(User user);
	
	int deleteUser(int id);
}
