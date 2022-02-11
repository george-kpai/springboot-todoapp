package com.qa.todoapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping
	public boolean testing() {
		return true;
	}
	
	@GetMapping("/current")
	public ResponseEntity<List<TodoDTO>> getCurrentTodos(@RequestParam(name = "id") Long id){
		return ResponseEntity.ok( service.getCurrentTodos(id) );
//		return null;
	}
	
	@PostMapping("/new")
	public ResponseEntity<TodoDTO> createTodo(@RequestBody Todo todo, 
			@RequestParam(name = "user_id") Long id){
		return ResponseEntity.ok( service.createTodo(todo, id) );
	}
	
	@PutMapping("/update")
	public ResponseEntity<TodoDTO> updateTodo(@RequestBody Todo todo){
		return ResponseEntity.ok( service.updateTodo(todo) );
	}
	
	@PutMapping("/mark-complete")
	public ResponseEntity<TodoDTO> markComplete(
			@RequestParam(name = "id") Long id, 
			@RequestParam(name = "complete") boolean complete){
		return ResponseEntity.ok( service.markComplete(id, complete) );
	}
	
	@DeleteMapping("/delete")
	public boolean deleteTodo(@RequestParam(name = "id") Long id) {
		return this.service.deleteTodo(id);
	}
}
