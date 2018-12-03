package com.servlet.angular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.servlet.angular.model.User;
import com.servlet.angular.service.UserConnection;


public class UserDAOImpl implements UserDAO{
	
	private UserConnection userConnection=UserConnection.getInstance();
	
	public int createUser(User user) {
		System.out.println("Entering createUser("+user+") dao layer...");
		
		int savedUser=0;
		PreparedStatement ps=null;
		long savedUserId=0;
		long newUserId=0;
		
		try {
			Connection con=userConnection.getConnection();
			ResultSet rs=con.createStatement().executeQuery("SELECT max(ID) FROM USER");
			while (rs.next()) {
				savedUserId=rs.getInt(1);
				newUserId=savedUserId+1;
			}
			ps=con.prepareStatement("INSERT INTO USER(ID,USERNAME,ADDRESS,EMAIL) VALUES(?,?,?,?)");
			ps.setLong(1, newUserId);
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getEmail());
			
			// execute create SQL stetement
			int update=ps.executeUpdate();
			if(update>0){
				savedUser=update;
				/*ResultSet reSet=con.createStatement().executeQuery("SELECT * FROM USER WHERE ID="+newUserId+" AND USERNAME='"+user.getUsername()+"'");
				while (reSet.next()) {
					savedUser=new User();
					savedUser.setId(reSet.getLong(1));
					savedUser.setUsername(reSet.getString(2));
					savedUser.setAddress(reSet.getString(3));
					savedUser.setEmail(reSet.getString(4));
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return savedUser;
	}

	public List<User> fetchAllUsers() {
		
		System.out.println("Entering fetchAllUsers() dao layer...");
		
		List<User> list = new ArrayList<User>();
		
		try{
			Connection con = userConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM USER");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				User user = new User();
				
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				
				list.add(user);
			}
			con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int updateUser(User user) {

		System.out.println("Entering updateUser("+user+") dao layer...");
		
		int updatedUser=0;
		
		try {
			Connection con=userConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE USER SET USERNAME=?,ADDRESS=?,EMAIL=? WHERE ID=?");
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getAddress());
			ps.setString(3, user.getEmail());
			ps.setLong(4, user.getId());
			
			// execute update SQL stetement
			int update = ps.executeUpdate();
			
			con.close();
			
			if(update > 0){
				
				updatedUser = update;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return updatedUser;
	}

	@Override
	public int deleteUser(int id) {
		
		int IS_DELETED = 0;
		
		try{
			Connection con = userConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM USER WHERE ID=?");
			ps.setLong(1, id);
			
			int deletedUser=ps.executeUpdate();
			if(deletedUser > 0){
				IS_DELETED = deletedUser;
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return IS_DELETED;
	}

}
