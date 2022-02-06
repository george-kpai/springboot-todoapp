package com.qa.todoapp.services;


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
	
//	public List<TodoDTO> getCurrentTodos(){
//		
//	}

}
