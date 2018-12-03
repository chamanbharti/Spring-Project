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

@WebServlet("/user")
public class UserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 848711081250416911L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StringBuffer sb=new StringBuffer();
		try {
			//getting data from request
			BufferedReader reader=req.getReader();
			String line=null;
			while ((line=reader.readLine()) !=null) {
				sb.append(line);
			}
			System.out.println("SB:"+sb);
			
			//parsing data using JSON
			JSONParser parser=new JSONParser();
			JSONObject jsonUser=(JSONObject) parser.parse(sb.toString());
			String user=(String) jsonUser.get("name");
			String pwd = (String) jsonUser.get("pwd");
			
			//sending user name as a response
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			
			out.write("A new user has been created.");
			out.write("User Name : "+user+" and Password : "+pwd);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
