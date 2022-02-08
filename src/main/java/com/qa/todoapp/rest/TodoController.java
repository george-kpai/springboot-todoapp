package com.qa.todoapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todoapp.dtos.TodoDTO;
import com.qa.todoapp.persistence.model.Todo;
import com.qa.todoapp.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	private TodoService service;
	
	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@GetMapping("/current/{id}")
	public ResponseEntity<List<TodoDTO>> getCurrentTodos(@PathVariable Long id){
		List<Todo> todos = this.service.getCurrentTodos(id);
		List<TodoDTO> dtos = this.service.mapToDTOList(todos);
		return ResponseEntity.ok(dtos);
	}
}
