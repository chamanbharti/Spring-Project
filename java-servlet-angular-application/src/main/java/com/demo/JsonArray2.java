package com.demo;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class JsonArray2 {
	
	public static void main(String[] args) {
		
		List ja = new ArrayList();
		
		ja.add("Chaman Bharti");
		ja.add(new Integer(27));
		ja.add(60000);// 60000
		ja.add(new Double(60000));// 60000.0
		
		System.out.println(ja);// [Chaman Bharti, 27, 60000, 60000.0]
		
		String jsonText = JSONValue.toJSONString(ja);
		System.out.println(jsonText);// [Chaman Bharti, 27, 60000, 60000.0]
	}

}
