package com.qa.todoapp.persistence.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long todo_id;
	@Column(nullable = false, length = 55)
	private String description;
//	@Column(name = "date_added", columnDefinition = "DATE")
	private LocalDate dateAdded;
//	@Column(name = "date_due", columnDefinition = "DATE")
	private LocalDate dateDue;
	private boolean complete;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@Transient
	private Long user_id;
	
	public Todo() {}
	
	public Todo(Long id, String description, LocalDate dateAdded, 
			LocalDate dateDue, boolean complete) {
		this.todo_id = id;
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
	}
	
	public Todo(Long id, String description, LocalDate dateAdded, 
			LocalDate dateDue, boolean complete, Long user_id) {
		this.todo_id = id;
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
		this.user_id = user_id;
	}
	
	public Todo(String description, LocalDate dateAdded, 
			LocalDate dateDue, boolean complete) {
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
	}
	
	public Todo(String description, LocalDate dateAdded, 
			LocalDate dateDue, boolean complete, Long user_id) {
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
		this.user_id = user_id;
	}

	public Long getTodo_id() {
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
	
	
	

}
