package com.qa.todoapp.services;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.qa.todoapp.persistence.model.Todo;
import com.qa.todoapp.persistence.repos.TodoRepo;	

//@SpringBootTest
public class TodoServiceUnitTest {
	private TodoService service;
	private TodoRepo repo;
	private ModelMapper mapper;
	
	List<Todo> todos = new ArrayList<Todo>();
	List<TodoDTO> todoDTOs = new ArrayList<TodoDTO>();
	
	@BeforeEach
	void setUp(){
		service = mock(TodoService.class);
		repo = mock(TodoRepo.class);
		mapper = new ModelMapper();

		todos = new ArrayList<Todo>();
		todos.add(new Todo(1L, "Task 1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), false));
		todos.add(new Todo(2L, "Task 2", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1), true));
		
		todoDTOs = new ArrayList<TodoDTO>();
		todoDTOs.add(new TodoDTO(1L, "Task 1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), false));
		todoDTOs.add(new TodoDTO(2L, "Task 2", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1), true));
	}

	@Test
	void testMapToDTOList(){
		Mockito	.when(this.service.mapToDTOList(todos))
				.thenReturn(todoDTOs);
		
		Assertions	.assertThat(this.service.mapToDTOList(todos))
					.isEqualTo(todoDTOs);
	}
	
//	@Test
//	void testGetCurrentTodos() {
//		Mockito	.when(this.repo.getCurrentTodos(1L))
//				.thenReturn(todos);
//		Assertions	.assertThat(this.service.getCurrentTodos(1L))
//					.isEqualTo(todoDTOs);
//	}
}
