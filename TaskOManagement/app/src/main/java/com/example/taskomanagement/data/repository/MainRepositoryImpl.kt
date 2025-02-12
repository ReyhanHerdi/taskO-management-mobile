package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.api.ApiService
import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamResponse

class MainRepositoryImpl(
    private val apiService: ApiService
): MainRepository {
    override suspend fun getTeam(): TeamResponse = apiService.getTeams()
    override suspend fun getProject(): ProjectResponse = apiService.getProjects()
    override suspend fun getTask(): TaskResponse = apiService.getTasks()
}