package com.qa.todoapp.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todoapp.persistence.model.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {

}
