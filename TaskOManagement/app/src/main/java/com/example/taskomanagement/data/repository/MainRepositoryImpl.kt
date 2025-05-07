package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.api.ApiService
import com.example.taskomanagement.data.datastore.AuthDataStore
import com.example.taskomanagement.data.response.LoginResponse
import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskExecutorResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamOfUserResponse
import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val authDataStore: AuthDataStore
): MainRepository {
    override suspend fun register(
        name: String,
        email: String,
        password: String,
    ): UserResponse = apiService.register(name, email, password)

    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse = apiService.login(email, password)

    override suspend fun setUserLogin() = authDataStore.setUserLogin()
    override suspend fun setUserLogout() = authDataStore.setUserLogout()
    override suspend fun getUserLogin(): Boolean = authDataStore.getUserLogin()
    override suspend fun setUserId(uid: Int) = authDataStore.setUserId(uid)
    override suspend fun getUserId(): Int = authDataStore.getUserId()
    override suspend fun getUser(id: Int): UserResponse = apiService.getUser(id)
    override suspend fun getTeam(): TeamResponse = apiService.getTeams()
    override suspend fun getUserTeams(id: Int): TeamOfUserResponse = apiService.getUserTeams(id)
    override suspend fun getProject(): ProjectResponse = apiService.getProjects()
    override suspend fun getTask(): TaskResponse = apiService.getTasks()
    override suspend fun getTaskByExector(id: Int): TaskExecutorResponse = apiService.getTaskByExecutor(id)
}