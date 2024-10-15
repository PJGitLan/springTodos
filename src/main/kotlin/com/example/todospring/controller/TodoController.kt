package com.example.todospring.controller

import com.example.todospring.model.Todo
import com.example.todospring.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/todos")
class TodoController(private val todoService: TodoService) {

    @GetMapping
    @Operation(summary = "Get all todos", description = "Get all todos")
    fun getAllTodos(): List<Todo> = todoService.getAll()

    @PostMapping
    @Operation(summary = "Add a todo", description = "Add a todo")
    fun createTodo(@RequestBody todo: Todo) = todoService.create(todo)

    @GetMapping("/{id}")
    @Operation(summary = "Get a todo", description = "Get a todo by its ID")
    fun getTodoById(@PathVariable id: Long): Todo =
        todoService.getById(id);

    @PutMapping("/{id}")
    @Operation(summary = "Update a todo", description = "Updates a todo by its ID")
    fun updateTodo(@PathVariable id: Long, @RequestBody updatedTodo: Todo)
        = todoService.update(id, updatedTodo);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a todo", description = "Deletes a todo by its ID")
    fun deleteTodo(@PathVariable id: Long) = todoService.delete(id);
}