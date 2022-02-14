package com.qa.todoapp.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todoapp.dtos.TodoDTO;
import com.qa.todoapp.persistence.model.Todo;
import com.qa.todoapp.persistence.model.User;
import com.qa.todoapp.services.TodoService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:user-schema.sql", "classpath:todo-schema.sql",  
		"classpath:user-data.sql", "classpath:todo-data.sql"}, 
		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TodoController_IntTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ObjectMapper jsonifier;
	@Autowired
	private TodoService service;
	
	private User todoUserProp;			//-> This user is needed because all todos has a user property
	private TodoDTO _1st_Todo;
	private String allTodosJSON;
	private String currentTodosJSON;
	
	@BeforeEach
	void setUp() throws Exception{
		todoUserProp = new User();
		todoUserProp.setUser_id(1L);
		_1st_Todo = service.allTodos().get(0);
		allTodosJSON = jsonifier.writeValueAsString( service.allTodos() );
		currentTodosJSON = jsonifier.writeValueAsString( service.getCurrentTodos(todoUserProp.getUser_id()) );
	}
	
	@Test
	void testAllTodos() throws Exception {
		mock.perform(get("/todos"))
			.andExpect(status().is(200))
			.andExpect( content().json(allTodosJSON) );
	}
	
	@Test
	void testCreateTodo() throws Exception {
		//-> There is 1 user in database with id = 1
		//-> There are 2 todos in database with ids = 1 and 2
		Todo todo = new Todo("TaskH", LocalDate.of(2022, 5, 1), 
				LocalDate.of(2022, 5, 2), false, todoUserProp);
		TodoDTO respTodo = new TodoDTO(3L, "TaskH", LocalDate.of(2022, 5, 1),	// when persisted, this todo will get id = 3 
				LocalDate.of(2022, 5, 2), false, todoUserProp.getUser_id());
		
		mock.perform(post("/todos/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content( jsonifier.writeValueAsString(todo) )
				)	.andExpect(status().is2xxSuccessful())
					.andExpect( content().json( jsonifier.writeValueAsString(respTodo)) )
					;
		
	}
	
	 @Test
	 public void testGetCurrentTodos() throws Exception {
		 mock.perform(get("/todos/current").param("id", "1"))
		 			.andExpect(status().is(200))
		 			.andExpect( content().json(currentTodosJSON) )
		 			;
	 }

	 @Test
	 void testUpdateTodo() throws Exception {
		 Todo newTodo = new Todo(1L, "Task 123", LocalDate.of(2020, 2, 2), 
				 LocalDate.of(2020, 2, 2), false, todoUserProp);
		 TodoDTO updatedTodo = new TodoDTO(1L, "Task 123", LocalDate.of(2020, 2, 2), 
				 LocalDate.of(2020, 2, 2), false, todoUserProp.getUser_id());
		 
		 mock.perform( put("/todos/update")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content( jsonifier.writeValueAsString(newTodo) ))
		 			.andExpect(status().is(200))
		 			.andExpect( content().json( jsonifier.writeValueAsString(updatedTodo)) );
	 }
	 
	 @Test
	 void testMarkComplete() throws Exception {
		 _1st_Todo.setComplete(true);
		 		 
		 mock.perform( put("/todos/mark-complete")
				 .param("id", "1")
				 .param("complete", "True"))
		 			.andExpect( status().is(200))
		 			.andExpect( content().json( jsonifier.writeValueAsString(_1st_Todo)) );
	 }
	 
	 @Test
	 void testDeleteToo() throws Exception {
		 mock.perform( delete("/todos/delete").param("id", "1") )
		 		.andExpect( content().string("true") );
	 }

}
