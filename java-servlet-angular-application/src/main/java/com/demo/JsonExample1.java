package com.demo;

import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONObject;

public class JsonExample1 {
	
	public static void main(String[] args) throws IOException {
		
		JSONObject obj = new JSONObject();
		
		obj.put("name", "Chaman Bharti");
		obj.put("age", new Integer(27));
		obj.put("salary", new Double(60000));
		
		// 1 step to display
		System.out.println(obj);
		
		// 2 step to display
		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		
		String jsonText = out.toString();
		System.out.println(jsonText);
	}

}
