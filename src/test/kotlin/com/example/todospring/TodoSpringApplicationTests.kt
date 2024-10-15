package com.example.todospring

import com.example.todospring.controller.TodoController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class TodoSpringApplicationTests {

    @Autowired
    private val controller: TodoController? = null

    @Test
    fun contextLoads() {
        assertThat(controller).isNotNull();
    }

}
