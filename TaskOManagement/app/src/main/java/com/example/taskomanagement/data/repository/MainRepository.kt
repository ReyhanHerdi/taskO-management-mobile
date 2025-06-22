package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.response.LoginResponse
import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskExecutorResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamMemberResponse
import com.example.taskomanagement.data.response.TeamOfUserResponse
import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse

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
    suspend fun setUserId(uid: Int)
    suspend fun getUserId(): Int
    // Main
    suspend fun getUser(id: Int): UserResponse
    suspend fun getTeam(): TeamResponse
    suspend fun getUserTeams(id: Int): TeamOfUserResponse
    suspend fun getProject(teamId: Int): ProjectResponse
    suspend fun getTask(): TaskResponse
    suspend fun getTaskByExector(id: Int): TaskExecutorResponse
    suspend fun getTeamByUserId(id: Int): TeamMemberResponse
    suspend fun getTeamByTeamId(id: Int): TeamResponse
    suspend fun getProjectsByTeamId(id: Int): ProjectResponse
}