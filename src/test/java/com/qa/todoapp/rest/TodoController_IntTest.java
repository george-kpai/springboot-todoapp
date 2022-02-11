package com.qa.todoapp.rest;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todoapp.dtos.TodoDTO;
import com.qa.todoapp.persistence.model.Todo;
import com.qa.todoapp.services.TodoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:user-schema.sql", "classpath:user-data.sql", 
		"classpath:todo-schema.sql", "classpath:todo-data.sql"}, 
		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TodoController_IntTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ObjectMapper jsonifier;
	@Autowired
	private TodoService service;
	@Autowired
	private ModelMapper mapper;
	
	@Test
	public void defaultEndPoint() throws Exception {
		this.mock.perform(get("/todos")).andExpect(status().is(200));
	}
	
//	 @Test
//	 public void testGetCurrentTodos() throws Exception {
//		 //First get test data from test db
////		 List<TodoDTO> todos = service.getCurrentTodos(1L);
//		 List<TodoDTO> todos = Arrays.asList(
//				 new TodoDTO(1L, "Task 1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 2), false),
//				 new TodoDTO(2L, "Task 2", LocalDate.of(2021, 5, 5), LocalDate.of(2021, 6, 6), false)
//				 );
//		 //Generate JSON version of test data
//		 String todosJsonStr = jsonifier.writeValueAsString(todos);
//		 System.out.println(">>>===> AAA "+todosJsonStr);
//		 
//		 RequestBuilder mockRequest = get("/todos/current").param("id", "1");
//		 ResultMatcher matchStatus = status().is2xxSuccessful();
//		 ResultMatcher matchBody = content().json(todosJsonStr);
//		 this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
//		 
//		 
////		 this.mock.perform(get("/todos/current")
////				 .param("id", "1")
////				 .contentType(MediaType.APPLICATION_JSON)
////				 .
////				 )
////		 	.andExpect(status().is(200))
////		 	.andExpect(content().json(todosJsonStr))
////		 ;
//	 }

	 @Test
	 public void testCreateTodo() throws Exception{
		 //First create a Todo
		 Todo newTodo = new Todo("Task X", LocalDate.of(2020, 8, 4), 
				 LocalDate.of(2020, 3, 3), false, 1L);
		 //Convert it to string
		 String newTodoStr = jsonifier.writeValueAsString(newTodo);
		 
		 RequestBuilder mockRequest = post("/todos/new")
				 .contentType(MediaType.APPLICATION_JSON).content(newTodoStr);
		 
		 //the response 
		 Todo savedTodo = new Todo(3L, "Task X", LocalDate.of(2020, 8, 4), 
				 LocalDate.of(2020, 3, 3), false, 1L); //already persisted 1 & 2 in line 39
		 //response converted to string
		 String savedTodoStr = jsonifier.writeValueAsString(savedTodo);
		 
		 ResultMatcher matchStatus = status().is2xxSuccessful();
		 ResultMatcher matchBody = content().json(savedTodoStr);
		 mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	 }
	 
	 
}
