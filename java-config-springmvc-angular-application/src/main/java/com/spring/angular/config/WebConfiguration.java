package com.spring.angular.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
		return new DefaultServletHttpRequestHandler();
	}
	
	@Bean
	public SimpleUrlHandlerMapping  simpleUrlHandlerMapping (){
		
		Map<String, DefaultServletHttpRequestHandler> urlMap=new ManagedMap<>();
		urlMap.put("/**",defaultServletHttpRequestHandler());
		
		SimpleUrlHandlerMapping mapping=new SimpleUrlHandlerMapping();
		mapping.setUrlMap(urlMap);
		
		return mapping;
	}
	
	@Bean
	public DataSource dataSource() {
	    System.out.println("userDBDatasource :: init");
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/angularjs");
	    dataSource.setUsername("chaman_bharti");
	    dataSource.setPassword("chaman1234");
	    
	    return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(){
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource());
		
		return jdbcTemplate;
	}
	
}
