package com.qa.todoapp.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todoapp.dtos.UserDTO;
import com.qa.todoapp.persistence.model.User;
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
	
	public UserDTO createUser(User user) {
		return mapper.map(repo.save(user), UserDTO.class);
	}
	
	public UserDTO updateUser(User user) {
		Optional<User> optional = repo.findById(user.getId());
		User existing = optional.get();
		existing.setEmail(user.getEmail());
		existing.setName(user.getName());
		existing.setPassword(user.getPassword());
		return mapper.map(repo.save(existing), UserDTO.class);
	}
	
	public UserDTO userById(Long id) {
		Optional<User> optional = repo.findById(id);
		return mapper.map(optional.get(), UserDTO.class);
	}
	
	public boolean deleteUser(Long id) {
		repo.deleteById(id);
		boolean exists = repo.existsById(id);
		return !exists;
	}

}
