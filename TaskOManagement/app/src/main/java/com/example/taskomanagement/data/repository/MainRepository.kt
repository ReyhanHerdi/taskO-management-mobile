package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.response.LoginResponse
import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse
import java.util.concurrent.Flow

interface MainRepository {
    // Auth
    suspend fun register(
        name: String,
        email: String,
        password: String
    ): UserResponse
    suspend fun login(
        email: String,
        password: String
    ): LoginResponse
    suspend fun setUserLogin()
    suspend fun setUserLogout()
    suspend fun getUserLogin(): Boolean
    // Main
    suspend fun getTeam(): TeamResponse
    suspend fun getProject(): ProjectResponse
    suspend fun getTask(): TaskResponse
}