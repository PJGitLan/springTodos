package com.example.todospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoSpringApplication

fun main(args: Array<String>) {
    runApplication<TodoSpringApplication>(*args)
}
