package com.example.taskomanagement.data.api

import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/users")
    suspend fun getUsers() : UserResponse

    @GET("api/teams")
    suspend fun getTeams(): TeamResponse
}