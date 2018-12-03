package com.spring.angular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.angular.dao.UserDAO;
import com.spring.angular.model.User;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public int createUser(User user) {
		return userDAO.createUser(user);
	}

	@Override
	public List<User> fetchAllUsers() {
		return userDAO.fetchAllUsers();
	}

	@Override
	public int updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public int deleteUser(int id) {
		return userDAO.deleteUser(id);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
