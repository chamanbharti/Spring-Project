package com.spring.angular.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.angular.model.User;
@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
    
    @Override
	public int createUser(User user) {
    	
    	int count = 0;
    	Session session = this.sessionFactory.openSession();
    	session.save(user);
    	count++;
    	System.out.println("User created successfully...");
    	return count;
	}

	@Override
	public List<User> fetchAllUsers() {
		
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("from User").list();
		for(User user:users){
			logger.info("User List::"+user);
		}
		return users;
	}

	@Override
	public int updateUser(User user) {

		System.out.println("Entering updateUser("+user+") dao layer...");
		
		int updatedUser=0;
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		updatedUser++;
		System.out.println("User updated successfully...");
		return updatedUser;
	}

	@Override
	public int deleteUser(int id) {

		int deletedUser = 0;
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		User user = (User)session.load(User.class, new Long(id));
		session.delete(user);
		session.getTransaction().commit();
		deletedUser++;
		System.out.println("User deleted successfully...");
		return deletedUser;
	}

}
