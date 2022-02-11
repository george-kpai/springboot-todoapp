package com.qa.todoapp.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Todo> todos;
	
	
	public User() {}
	
	public User(Long user_id, String email, String name, String password) {
		this.user_id = user_id;
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
