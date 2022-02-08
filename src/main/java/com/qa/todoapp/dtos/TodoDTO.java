package com.qa.todoapp.dtos;

import java.time.LocalDate;

public class TodoDTO {
		private long id;
	private String description;
	private LocalDate dateAdded;
	private LocalDate dateDue;
	private boolean complete;
	
	public TodoDTO() {}
	
	public TodoDTO(long id, String description, LocalDate dateAdded, LocalDate dateDue, boolean complete) {
		this.id = id;
		this.description = description;
		this.dateAdded = dateAdded;
		this.dateDue = dateDue;
		this.complete = complete;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
