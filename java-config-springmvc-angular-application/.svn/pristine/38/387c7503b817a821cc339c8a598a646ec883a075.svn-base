package com.spring.angular.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.angular.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	
	long savedUserId=0;
	long newUserId=0;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
    
    @SuppressWarnings("deprecation")
	@Override
	public int createUser(User user) {
    	
    	 int count=0;
    	 
		 String sql = "SELECT max(ID) FROM USER";
		 savedUserId = jdbcTemplate.queryForLong(sql);
		 if(savedUserId > 0) {
			 newUserId=savedUserId+1;
			 String insertSQL = "INSERT INTO USER(ID,USERNAME,ADDRESS,EMAIL) VALUES(?,?,?,?)";
			 count=jdbcTemplate.update(insertSQL, new Object[]{newUserId,user.getUsername(),user.getAddress(),user.getEmail()});
		 }
		 return count;
	}
    

	@Override
	public List<User> fetchAllUsers() {
		String SQL="SELECT * FROM USER";
		List<User> users=jdbcTemplate.query(SQL, new UserRowMapper());
		
		return users;
	}

	@Override
	public int updateUser(User user) {

		System.out.println("Entering updateUser("+user+") dao layer...");
		
		int updatedUser=0;
		 String sql="update user set username='"+user.getUsername()+"', address='"+user.getAddress()+"',email='"+user.getEmail()+"' where id='"+user.getId()+"' ";
		// execute update SQL stetement
		updatedUser = jdbcTemplate.update(sql);
					
		return updatedUser;
	}

	@Override
	public int deleteUser(int id) {

		int IS_DELETED = 0;
		
		int deletedUser=jdbcTemplate.update("DELETE FROM USER WHERE ID="+id+"");
		
		if(deletedUser > 0){
			IS_DELETED = deletedUser;
		}
			
		return IS_DELETED;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
