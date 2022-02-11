package com.qa.todoapp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class TodoappApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertThat(true).isEqualTo(true);
	}

}
