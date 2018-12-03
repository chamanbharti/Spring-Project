package com.servlet.angular.service;

import java.util.List;

import com.servlet.angular.dao.UserDAO;
import com.servlet.angular.dao.UserDAOImpl;
import com.servlet.angular.model.User;

public class UserServiceImpl implements UserService{

	private UserDAO userDAO=null;
	
	public UserServiceImpl(){
		userDAO=new UserDAOImpl();
	}
	
	@Override
	public int createUser(User user) {
		System.out.println("Entering createUser("+user+") service layer...");
		return userDAO.createUser(user);
	}

	@Override
	public List<User> fetchAllUsers() {
		System.out.println("Entering fetchAllUsers() service layer...");
		
		return userDAO.fetchAllUsers();
	}

	public int updateUser(User user) {
		
		return userDAO.updateUser(user);
	}

	@Override
	public int deleteUser(int id) {
		
		 return userDAO.deleteUser(id);
	}


}
