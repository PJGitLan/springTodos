package com.example.todospring

import com.example.todospring.model.Todo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpTodoTest {

    @LocalServerPort
    private var port = 8080;

    @Autowired
    private lateinit var restTemplate: TestRestTemplate;

    private val baseUri = "http://localhost:$port/api/v1/todos";

    @Test
    fun addAndGetTodo() {
        val newTodo = Todo(title = "title", completed = false);
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

       val body = restTemplate.postForEntity(baseUri, HttpEntity(newTodo, headers), Todo::class.java).body;
        assertThat(body).isNotNull

        assertThat(
            restTemplate.getForObject(
                baseUri,
                Array<Todo>::class.java
            )
        ).contains(newTodo.copy(id = body!!.id, newTodo.title, newTodo.completed));
    }

    @Test
    fun addAndDeleteTodo() {
        val newTodo = Todo(title = "title", completed = false);
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body = restTemplate.postForEntity(baseUri, HttpEntity(newTodo, headers), Todo::class.java).body;
        assertThat(body).isNotNull

        val idUri = "$baseUri/${body!!.id}";
        restTemplate.delete(
            idUri
        );

        assertThat(restTemplate.getForEntity(idUri, Todo::class.java).statusCode).isEqualTo(
            HttpStatus.NOT_FOUND);
    }

    @Test
    fun addAndEditTodo() {
        val newTodo = Todo(title = "title", completed = false);
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body = restTemplate.postForEntity(baseUri, HttpEntity(newTodo, headers), Todo::class.java).body;
        assertThat(body).isNotNull

        val idUri = "$baseUri/${body!!.id}";

        val updatedTodo = Todo(title = "new title", completed = false)
        restTemplate.put(idUri, updatedTodo)

        assertThat(restTemplate.getForEntity(idUri, Todo::class.java).body).isEqualTo(
            updatedTodo.copy( id = body.id ));
    }
}