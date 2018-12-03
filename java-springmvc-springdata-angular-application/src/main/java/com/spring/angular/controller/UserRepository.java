package com.spring.angular.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.angular.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
