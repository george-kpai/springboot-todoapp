package com.qa.todoapp.services;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
	
	
	/***
	 * Private method for mapping List of Todos to DTOs
	 * @param todos
	 * @return
	 */
	public List<TodoDTO> mapToDTOList(List<Todo> todos){
		return todos.stream().map(t -> mapper.map(t, TodoDTO.class)).toList();
	}
	
	public List<TodoDTO> getCurrentTodos(Long user_id){
		List<Todo> todos = repo.getCurrentTodos(user_id);
		return mapToDTOList(todos);
	}
	
	private Date toDate(LocalDate d) {
		Date date = new Date(d.getYear(), d.getMonthValue(), d.getDayOfMonth());
		return date;
	}
	
	public TodoDTO createTodo(Todo todo, Long user_id) {
		Todo saved = repo.save(todo);
		saved.setTodo_id(user_id);
		Todo saved_id = repo.save(saved);
//		Date date_added = toDate(todo.getDateAdded());
//		Date date_due = toDate(todo.getDateDue());
//		Todo saved = repo.saveTodo(todo.getDescription(), date_added, 
//				date_due, todo.isComplete(), user_id);
		return mapper.map( saved_id, TodoDTO.class );
	}
	
	public TodoDTO updateTodo(Todo todo) {
		Optional<Todo> optional = repo.findById(todo.getTodo_id());
		Todo existing = optional.get();
		existing.setComplete(todo.isComplete());
		existing.setDateAdded(todo.getDateAdded());
		existing.setDateDue(todo.getDateDue());
		existing.setDescription(todo.getDescription());
		Todo saved = repo.save(existing);
		return mapper.map(saved, TodoDTO.class);
	}
	
	public TodoDTO markComplete(Long id, boolean complete) {
		Optional<Todo> optional = repo.findById(id);
		Todo existing = optional.get();
		existing.setComplete(complete);
		Todo saved = repo.save(existing);
		return mapper.map(saved, TodoDTO.class);
	}
	
	public boolean deleteTodo(Long id) {
		repo.deleteById(id);
		boolean exists = repo.existsById(id);
		return !exists;
	}

}
