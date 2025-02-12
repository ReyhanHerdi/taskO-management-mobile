package com.example.taskomanagement.data.api

import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/users")
    suspend fun getUsers() : UserResponse

    @GET("api/teams")
    suspend fun getTeams(): TeamResponse

    @GET("api/projects")
    suspend fun getProjects(): ProjectResponse

    @GET("api/tasks")
    suspend fun getTasks(): TaskResponse
}