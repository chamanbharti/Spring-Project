package com.spring.angular.config;

import java.util.Map;

import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

//This configuration class can be treated as a replacement of spring-servlet.xml as it contains all the information required for component-scanning and view resolver.
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.angular")
public class WebConfiguration {
	
	@Bean
	public DefaultServletHttpRequestHandler defaultServletHttpRequestHandler(){
		System.out.println("########### WebConfiguration DefaultServletHttpRequestHandler Initilization###################");
		return new DefaultServletHttpRequestHandler();
	}
	
	@Bean
	public SimpleUrlHandlerMapping  simpleUrlHandlerMapping (){
		System.out.println("########### WebConfiguration SimpleUrlHandlerMapping Initilization###################");
		@SuppressWarnings("rawtypes")
		Map<String, DefaultServletHttpRequestHandler> urlMap=new ManagedMap();
		urlMap.put("/**",defaultServletHttpRequestHandler());
		
		SimpleUrlHandlerMapping mapping=new SimpleUrlHandlerMapping();
		mapping.setUrlMap(urlMap);
		
		return mapping;
	}
	
}
