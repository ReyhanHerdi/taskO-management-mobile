package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.response.TeamResponse

interface MainRepository {
    suspend fun getTeam(): TeamResponse
}