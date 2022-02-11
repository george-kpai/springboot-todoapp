package com.qa.todoapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todoapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

}
