package com.qa.todoapp.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todoapp.dtos.UserDTO;
import com.qa.todoapp.persistence.model.User;
import com.qa.todoapp.persistence.repos.UserRepo;

@SpringBootTest
public class UserServiceUnitTest {
	@Autowired
	private UserService service;
	@MockBean
	private UserRepo repo;
//	@Autowired
//	private ModelMapper mapper;
	
	
	@Test
	public void testCreateUser() {
		User user = new User("john.doe@email.com", "John", "abc123");
		User savedUser = new User(1L, "john.doe@email.com", "John", "abc123");
		UserDTO savedUserDto = new UserDTO(1L, "john.doe@email.com", "John");
		
		Mockito	.when( repo.save(user) )
				.thenReturn(savedUser);
		Assertions.assertThat(service.createUser(user))
				.isEqualTo(savedUserDto);
	}
	
	@Test
	public void testUpdateUser() {		
		User user = new User("george@email.com", "Johnny", "abc123");
		User existing = new User(1L, "george@email.com", "George Kpai", "abc123");
		User updated = new User(1L, "george@email.com", "Johnny", "abc123");
		UserDTO updatedDTO = new UserDTO(1L, "george@email.com", "Johnny");
		Optional<User> opt = Optional.of(existing);
		
		Mockito	.when( repo.findById(user.getUser_id()) )
				.thenReturn( opt );
		
		Mockito	.when( repo.save(existing) )
				.thenReturn(updated);
		Assertions.assertThat(service.updateUser(user))
				.isEqualTo(updatedDTO);
	}
	
	@Test 
	public void testUserById() {
		User user = new User(1L, "george@email.com", "George Kpai", "abc123");
		UserDTO userDTO = new UserDTO(1L, "george@email.com", "George Kpai");
		Optional<User> opt = Optional.of(user);
		
		Mockito	.when( repo.findById(1L))
				.thenReturn( opt );
		Assertions.assertThat( service.userById(1L))
				.isEqualTo(userDTO);
	}
	
	@Test
	public void testDeleteUser() {
		Mockito	.when( repo.existsById(1L) )
				.thenReturn(false);
		Assertions.assertThat( service.deleteUser(1L) )
				.isEqualTo(true);
	}

}
