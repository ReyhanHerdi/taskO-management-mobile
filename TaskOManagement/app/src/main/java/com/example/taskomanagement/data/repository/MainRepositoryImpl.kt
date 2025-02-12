package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.api.ApiService
import com.example.taskomanagement.data.response.TeamResponse

class MainRepositoryImpl(
    private val apiService: ApiService
): MainRepository {
    override suspend fun getTeam(): TeamResponse = apiService.getTeams()
}