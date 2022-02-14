package com.qa.todoapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todoapp.dtos.UserDTO;
import com.qa.todoapp.persistence.model.User;
import com.qa.todoapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping //Default 
	public ResponseEntity<List<UserDTO>> allUsers(){
		return ResponseEntity.ok( service.allUsers() );
	}
	
	@PostMapping("/new")
	public ResponseEntity<UserDTO> createUser(@RequestBody User user){
		return ResponseEntity.ok( service.createUser(user) );
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDTO> updateUser(@RequestBody User user){
		return ResponseEntity.ok( service.updateUser(user) );		
	}
	
	@DeleteMapping("/delete")
	public boolean deleteUser(@RequestParam(name = "id") Long id) {
		return service.deleteUser(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable Long id){
		return ResponseEntity.ok( service.userById(id) );
	}

}
