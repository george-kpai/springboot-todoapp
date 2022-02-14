package com.qa.todoapp.persistence.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name = "date_added", columnDefinition = "DATE")
	private LocalDate dateAdded;
	@Column(name = "date_due", columnDefinition = "DATE")
	private LocalDate dateDue;
	private boolean complete;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Todo() {}
	
	public Todo(Long id) {
		this.todo_id = id;
	}

	public Todo(Long todo_id, String description, LocalDate dateAdded, LocalDate dateDue, boolean complete, User user) {
		super();
		this.todo_id = todo_id;
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
		this.user = user;
	}
		
	public Todo(String description, LocalDate dateAdded, LocalDate dateDue, boolean complete, User user) {
		super();
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(complete, dateAdded, dateDue, description, todo_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return complete == other.complete && Objects.equals(dateAdded, other.dateAdded)
				&& Objects.equals(dateDue, other.dateDue) && Objects.equals(description, other.description)
				&& Objects.equals(todo_id, other.todo_id);
	}

	
	
}
