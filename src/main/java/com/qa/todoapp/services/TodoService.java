package com.qa.todoapp.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todoapp.dtos.TodoDTO;
import com.qa.todoapp.persistence.model.Todo;
import com.qa.todoapp.persistence.repos.TodoRepo;

@Service
public class TodoService {
	private TodoRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public TodoService(TodoRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public TodoDTO mapToDTO(Todo todo) {
		return mapper.map(todo, TodoDTO.class);
	}
	
	/***
	 * Private method for mapping List of Todos to DTOs
	 * @param todos
	 * @return
	 */
	public List<TodoDTO> mapToDTOList(List<Todo> todos){
		List<TodoDTO> dtos = new ArrayList<>();
		todos.forEach(t -> {
			dtos.add(mapToDTO(t));
		});
		return dtos;
	}
	
	public List<Todo> getCurrentTodos(Long user_id){
		List<Todo> todos = repo.getCurrentTodos(user_id);
		return todos;
	}
	
	public Todo createTodo(Todo todo) {
		return repo.save(todo);
	}
	
	public Todo updateTodo(Todo todo) {
		Optional<Todo> optional = repo.findById(todo.getTodo_id());
		Todo existing = optional.get();
		existing.setComplete(todo.isComplete());
		existing.setDateAdded(todo.getDateAdded());
		existing.setDateDue(todo.getDateDue());
		existing.setDescription(todo.getDescription());
		return repo.save(existing);
	}
	
	public Todo markComplete(Long id, boolean complete) {
		Optional<Todo> optional = repo.findById(id);
		Todo existing = optional.get();
		existing.setComplete(complete);
		return repo.save(existing);
	}
	
	public boolean deleteTodo(Long id) {
		repo.deleteById(id);
		boolean exists = repo.existsById(id);
		return !exists;
	}

}
