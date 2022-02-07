package com.qa.todoapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todoapp.persistence.repos.UserRepo;

@Service
public class UserService {
	private UserRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public UserService(UserRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

}
