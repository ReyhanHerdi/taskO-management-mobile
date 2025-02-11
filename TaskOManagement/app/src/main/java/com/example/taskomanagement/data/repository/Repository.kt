package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.api.ApiConfig
import com.example.taskomanagement.data.response.TeamResponse

class Repository {
    suspend fun getTeam(): TeamResponse =  ApiConfig.getApiService().getTeams()
}