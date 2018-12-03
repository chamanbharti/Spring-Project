package com.demo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class JsonExample2 {
	
	public static void main(String[] args) {
		
		Map obj = new HashMap();
		
		obj.put("name", "Chaman Bharti");
		obj.put("age", new Integer(27));
		obj.put("salary", new Double(60000));
		
		System.out.println(obj);//{name=Chaman Bharti, salary=60000.0, age=27}
		
		String jsonText = JSONObject.toJSONString(obj);
		System.out.println(jsonText);//{name=Chaman Bharti, salary=60000.0, age=27}
		
	}

}
