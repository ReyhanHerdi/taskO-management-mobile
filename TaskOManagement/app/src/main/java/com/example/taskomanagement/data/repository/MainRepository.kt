package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse

interface MainRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): UserResponse
    suspend fun getTeam(): TeamResponse
    suspend fun getProject(): ProjectResponse
    suspend fun getTask(): TaskResponse
}