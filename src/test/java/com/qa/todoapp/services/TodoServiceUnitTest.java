package com.qa.todoapp.services;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todoapp.dtos.TodoDTO;
import com.qa.todoapp.dtos.UserDTO;
import com.qa.todoapp.persistence.model.Todo;
import com.qa.todoapp.persistence.model.User;
import com.qa.todoapp.persistence.repos.TodoRepo;	

@SpringBootTest
public class TodoServiceUnitTest {
	@Autowired
	TodoService service;
	@MockBean
	TodoRepo repo;
	@MockBean
	UserService usrService;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	private User user_ID;				//-> User with only ID field for todoDTOs 
	private User dbUser;
	private UserDTO dbUserDTO;
	
	@BeforeEach
	void setUp(){
		user_ID = new User();
		user_ID.setUser_id(1L);
		dbUser = new User("name@email.com", "John", "123");
		dbUserDTO = new UserDTO(1L, "name@email.com", "John");

		todos = new ArrayList<Todo>();
		todos.add(new Todo(1L, "Task 1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), false, user_ID));
		todos.add(new Todo(2L, "Task 2", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1), true, user_ID));
		
		todoDTOs = new ArrayList<TodoDTO>();
		todoDTOs.add(new TodoDTO(1L, "Task 1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), false, 1L));
		todoDTOs.add(new TodoDTO(2L, "Task 2", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1), true, 1L));
	}

	@Test
	void testMapToDTOList(){
		Assertions	.assertThat(service.mapToDTOList(todos))
					.containsExactlyElementsOf(todoDTOs);
	}
	
	@Test
	void testGetCurrentTodos() {
		Long usrId = 1L;
		
		Mockito	.when( repo.getCurrentTodos( usrId ) )
				.thenReturn(todos);
		Assertions.assertThat(this.service.getCurrentTodos(usrId))
				.containsExactlyElementsOf(todoDTOs);
	}
	
	@Test
	void testCreateTodo() {
		Todo todo = new Todo("TaskX", LocalDate.of(2022, 5, 1), LocalDate.of(2022, 5, 1), false, user_ID);
		Todo savedTodo = new Todo(1L, "TaskX", LocalDate.of(2022, 5, 1), LocalDate.of(2022, 5, 1), false, user_ID);
		TodoDTO savedTodoDTO = new TodoDTO(1L, "TaskX", LocalDate.of(2022, 5, 1), LocalDate.of(2022, 5, 1), false, 1L);
		
		Mockito	.when( usrService.createUser(dbUser) )
				.thenReturn(dbUserDTO);
		Mockito	.when( repo.save(todo) )
				.thenReturn(savedTodo);
		Assertions.assertThat( service.createTodo(todo) )
				.isEqualTo(savedTodoDTO);
	}
	
	@Test
	void testUpdateTodo() {
		Todo todo = new Todo(1L, "TaskY", LocalDate.of(2022, 5, 5), LocalDate.of(2022, 5, 6), false, user_ID);
		Todo existing = new Todo(1L, "TaskX", LocalDate.of(2022, 5, 1), LocalDate.of(2022, 5, 1), false, user_ID);
		Optional<Todo> opt = Optional.of(existing);
		Todo updated = new Todo(1L, "TaskY", LocalDate.of(2022, 5, 5), LocalDate.of(2022, 5, 6), false, user_ID);
		TodoDTO updatedDTO = new TodoDTO(1L, "TaskY", LocalDate.of(2022, 5, 5), LocalDate.of(2022, 5, 6), false, 1L);
		
		Mockito	.when( repo.findById(todo.getTodo_id()) )
				.thenReturn(opt);
		Mockito	.when( repo.save(updated) )
				.thenReturn(updated);
		Assertions.assertThat( service.updateTodo(todo) )
				.isEqualTo(updatedDTO);
	}
	
	@Test
	void testMarkComplete() {
		Long usrId = 1L;
		boolean complete = true;
		Todo existing = new Todo(4L, "Task T", LocalDate.of(2022, 5, 5), LocalDate.of(2022, 5, 6), false, user_ID);
		Optional<Todo> opt = Optional.of(existing);
		Todo updated = new Todo(4L, "Task T", LocalDate.of(2022, 5, 5), LocalDate.of(2022, 5, 6), complete, user_ID);
		Todo saved = new Todo(4L, "Task T", LocalDate.of(2022, 5, 5), LocalDate.of(2022, 5, 6), complete, user_ID);
		TodoDTO savedDto = new TodoDTO(4L, "Task T", LocalDate.of(2022, 5, 5), LocalDate.of(2022, 5, 6), true, usrId);
		
		Mockito	.when( repo.findById(usrId) )
				.thenReturn(opt);
		Mockito	.when( repo.save(updated) )
				.thenReturn(saved);
		Assertions.assertThat( service.markComplete(usrId, complete))
				.isEqualTo(savedDto);
	}
	
	@Test
	void testDeleteTodo() {
		Long usrId = 1L;
		boolean exists = false;
		
		Mockito	.when( repo.existsById(usrId) )
				.thenReturn(exists);
		Assertions.assertThat( service.deleteTodo(usrId) )
				.isEqualTo(!exists);
	}
	
}
