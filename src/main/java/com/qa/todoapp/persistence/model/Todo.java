package com.qa.todoapp.persistence.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long todo_id;
	@Column(nullable = false, length = 55)
	private String description;
	private LocalDate dateAdded;
	private LocalDate dateDue;
	private boolean complete;
	@ManyToOne()
	@JoinColumn(name = "id", nullable = false)
	private User user;
	
	public Todo() {}
	
	public Todo(Long id, String description, LocalDate dateAdded, 
			LocalDate dateDue, boolean complete) {
		this.todo_id = id;
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
		//TODO: Do I need to add user as a constructor parameter?
//		this.user = user;
	}

	public long getTodo_id() {
		return todo_id;
	}

	public void setTodo_id(Long id) {
		this.todo_id = id;
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
	
	//TODO: Do I need to specify getters and setters for Joined Columns?
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}
