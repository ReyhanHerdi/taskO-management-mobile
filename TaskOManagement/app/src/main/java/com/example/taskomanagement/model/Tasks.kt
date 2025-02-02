package com.example.taskomanagement.model

data class Tasks(
    val nameTask: String,
    val project: String,
    val description: String,
    val due: String,
    val status: String
)