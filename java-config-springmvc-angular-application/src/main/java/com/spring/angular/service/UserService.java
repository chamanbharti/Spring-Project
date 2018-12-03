package com.spring.angular.service;

import java.util.List;

import com.spring.angular.model.User;

public interface UserService {
	int createUser(User user);
	List<User> fetchAllUsers();
	int updateUser(User user);
	int deleteUser(int id);
}
