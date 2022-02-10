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
	
	@GetMapping("/current")
	public ResponseEntity<List<TodoDTO>> getCurrentTodos(@RequestParam(name = "id") Long id){
		List<Todo> todos = this.service.getCurrentTodos(id);
		List<TodoDTO> dtos = this.service.mapToDTOList(todos);
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping("/new")
	public ResponseEntity<TodoDTO> createTodo(@RequestBody Todo todo){
		Todo dbTodo = this.service.createTodo(todo);
		return ResponseEntity.ok(this.service.mapToDTO(dbTodo));
	}
	
	@PutMapping("/update")
	public ResponseEntity<TodoDTO> updateTodo(@RequestBody Todo todo){
		Todo updated = this.service.updateTodo(todo);
		return ResponseEntity.ok(this.service.mapToDTO(updated));
	}
	
	@PutMapping("/mark-complete")
	public ResponseEntity<TodoDTO> markComplete(
			@RequestParam(name = "id") Long id, 
			@RequestParam(name = "complete") boolean complete){
		Todo completed = this.service.markComplete(id, complete);
		return ResponseEntity.ok(this.service.mapToDTO(completed));
	}
	
	@DeleteMapping("/delete")
	public boolean deleteTodo(@RequestParam(name = "id") Long id) {
		return this.service.deleteTodo(id);
	}
}
