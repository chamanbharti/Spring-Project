package com.servlet.angular;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.servlet.angular.model.User;
import com.servlet.angular.service.UserServiceImpl;

@WebServlet("/fetch_users")
public class FetchUsersServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Entering FetchUsers doGet() Controller layer.......");
		
		Gson gson = new Gson();
		String users = null;
		List<User> usersList = new ArrayList<User>();
		
		try{
			 usersList = new UserServiceImpl().fetchAllUsers();
			
			 users = gson.toJson(usersList);
			 System.out.println("JSONList:"+users);
			//sending user name as a response
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			out.write(users);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
