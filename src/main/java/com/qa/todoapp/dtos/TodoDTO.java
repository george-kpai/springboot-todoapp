package com.qa.todoapp.dtos;

import java.time.LocalDate;
import java.util.Objects;

import com.qa.todoapp.persistence.model.User;

public class TodoDTO {
	private Long todo_id;
	private String description;
	private LocalDate dateAdded;
	private LocalDate dateDue;
	private boolean complete;
	private Long user_id;
	
	public TodoDTO() {}
	
	public TodoDTO(Long id, String description, LocalDate dateAdded, LocalDate dateDue, boolean complete, Long user_id) {
		this.todo_id = id;
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
		this.user_id = user_id;
	}
	
	public TodoDTO(String description, LocalDate dateAdded, LocalDate dateDue, boolean complete, Long user_id) {
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
		this.user_id = user_id;
	}
	
	

	public Long getTodo_id() {
		return todo_id;
	}

	public void setTodo_id(Long todo_id) {
		this.todo_id = todo_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

	public LocalDate getDateDue() {
		return dateDue;
	}

	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(complete, dateAdded, dateDue, description, todo_id, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoDTO other = (TodoDTO) obj;
		return complete == other.complete && Objects.equals(dateAdded, other.dateAdded)
				&& Objects.equals(dateDue, other.dateDue) && Objects.equals(description, other.description)
				&& Objects.equals(todo_id, other.todo_id) && Objects.equals(user_id, other.user_id);
	}

		
}
