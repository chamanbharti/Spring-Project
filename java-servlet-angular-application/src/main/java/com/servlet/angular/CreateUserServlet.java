package com.servlet.angular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.servlet.angular.model.User;
import com.servlet.angular.service.UserService;
import com.servlet.angular.service.UserServiceImpl;

@WebServlet("/create_user")
public class CreateUserServlet extends HttpServlet{

	private static final long serialVersionUID = 3513677837431359349L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Entering doPost().......");
		
		JSONObject jsonObject=parseRequest(req);
		User user=populateUserModel(jsonObject);
		UserService userService=new UserServiceImpl();
		int createdUser=userService.createUser(user);
		
		//sending user name as a response
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		if(createdUser>0)
			out.write("user created");
		else
			out.write("user not created");
		out.flush();
		out.close();
	}
	
	private User populateUserModel(JSONObject jsonObject){
		
		User user=new User();
		
		String id=(String) jsonObject.get("id");
		if(id !=null){
			long userId=Long.parseLong(id);
			user.setId(userId);
		}
		
		String username=(String) jsonObject.get("username");
		if(!username.isEmpty()){
			user.setUsername(username);
		}
		
		String address=(String) jsonObject.get("address");
		if(!address.isEmpty()){
			user.setAddress(address);
		}
		
		String email=(String) jsonObject.get("email");
		if(!email.isEmpty()){
			user.setEmail(email);
		}
		
		return user;
	}
	
	private JSONObject parseRequest(HttpServletRequest request){
		JSONObject jsonUser=null;
		StringBuffer sb=new StringBuffer();
		try {
			//getting data from request
			BufferedReader reader=request.getReader();
			String line=null;
			while ((line=reader.readLine()) !=null) {
				sb.append(line);
			}
			System.out.println("Comming JSON:"+sb);
			
			//Gson gson=new Gson();
			//gson.toJson(sb.toString());
			//User user=gson.fromJson(gson.toJson(sb.toString()), User.class);
			//parsing data using JSON
			JSONParser parser=new JSONParser();
			jsonUser=(JSONObject) parser.parse(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonUser;
	}
	
	private JSONObject createJSON(User user){
		JSONObject json=new JSONObject();
		json.put("id", user.getId());
		json.put("username", user.getUsername());
		json.put("address", user.getAddress());
		json.put("email", user.getEmail());
		
		return json;
	}
}
