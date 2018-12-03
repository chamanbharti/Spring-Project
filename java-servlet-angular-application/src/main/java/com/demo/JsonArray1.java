package com.demo;

import org.json.simple.JSONArray;

public class JsonArray1 {
	
	public static void main(String[] args) {
		
		JSONArray ja = new JSONArray();
		
		ja.add("Chaman Bharti");
		ja.add(new Integer(27));
		ja.add(60000);// 60000
		ja.add(new Double(60000));// 60000.0
		
		System.out.println(ja);
	}

}
