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

import com.servlet.angular.service.UserService;
import com.servlet.angular.service.UserServiceImpl;

@WebServlet("/delete_user")
public class DeleteUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Entering DeleteUserServlet doDelete().......");
		
		int userId=0;
		JSONObject jsonObject=parseRequest(req);
		if(jsonObject!=null)
			//userId=(int) jsonObject.get("id");
			userId = Integer.parseInt(String.valueOf(jsonObject.get("id")));
		
		int deletedUser=0;
		if(userId > 0){
			UserService userService=new UserServiceImpl();
			deletedUser=userService.deleteUser(userId);
		}
		
		//sending user name as a response
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		if(deletedUser>0)
			out.write("user deleted");
		else
			out.write("user not deleted");
		out.flush();
		out.close();
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
			
			//parsing data using JSON
			JSONParser parser=new JSONParser();
			jsonUser=(JSONObject) parser.parse(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonUser;
	}
	
}
	
