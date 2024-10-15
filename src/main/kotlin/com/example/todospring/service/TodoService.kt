package com.example.todospring.service

import com.example.todospring.model.Todo
import com.example.todospring.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TodoService(private val repository: TodoRepository) {
    // Note: a service is not really necessary in this case
    fun getAll(): List<Todo> = repository.findAll()

    fun create(todo: Todo) = repository.save(todo)

    fun getById(id: Long): Todo =
        repository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    fun update(id: Long, updatedTodo: Todo): Todo {
        return if (repository.existsById(id)) {
            repository.save(updatedTodo.copy(id = id, title = updatedTodo.title, completed = updatedTodo.completed))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun delete(id: Long) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}