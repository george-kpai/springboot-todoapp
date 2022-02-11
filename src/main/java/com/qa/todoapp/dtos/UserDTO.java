package com.qa.todoapp.dtos;

import java.util.Objects;

public class UserDTO {
	private Long user_id;
	private String email;
	private String name;
	
	public UserDTO() {}
		
	public UserDTO(Long user_id, String email, String name) {
		this.user_id = user_id;
		this.email = email;
		this.name = name;
	}

	public UserDTO(String email, String name) {
		this.email = email;
		this.name = name;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, name, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(user_id, other.user_id);
	}
	
	
}
