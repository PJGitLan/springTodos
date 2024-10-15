package com.example.todospring.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Todo(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    var title: String = "",
    var completed: Boolean = false
)