package com.qa.todoapp.persistence.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.todoapp.persistence.model.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
	@Query("SELECT t FROM User u INNER JOIN u.todos t "
			+ "WHERE u.id = ?1 AND t.complete = TRUE")
	List<Todo> getCurrentTodos(Long user_id);
	//TODO: Research "WHERE t.complete = TRUE" i.e hard coded
}
