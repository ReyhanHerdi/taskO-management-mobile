package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamResponse

interface MainRepository {
    suspend fun getTeam(): TeamResponse
    suspend fun getProject(): ProjectResponse
    suspend fun getTask(): TaskResponse
}