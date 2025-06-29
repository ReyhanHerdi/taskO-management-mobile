package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.api.ApiService
import com.example.taskomanagement.data.datastore.AuthDataStore
import com.example.taskomanagement.data.response.LoginResponse
import com.example.taskomanagement.data.response.ProjectDetailResponse
import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.ProjectWithTaskResponse
import com.example.taskomanagement.data.response.TaskDetailResponse
import com.example.taskomanagement.data.response.TaskExecutorResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamMemberResponse
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
    override suspend fun getProject(teamId: Int): ProjectWithTaskResponse = apiService.getProjects(teamId)
    override suspend fun getTask(): TaskResponse = apiService.getTasks()
    override suspend fun getTaskById(id: Int): TaskDetailResponse = apiService.getTaskById(id)
    override suspend fun getTaskByExector(id: Int): TaskExecutorResponse = apiService.getTaskByExecutor(id)
    override suspend fun getTeamByUserId(id: Int): TeamMemberResponse = apiService.getTeamsByUserId(id)
    override suspend fun getTeamByTeamId(id: Int): TeamResponse = apiService.getTeamById(id)
    override suspend fun getProjectsByTeamId(id: Int): ProjectResponse = apiService.getProjectsByTeamId(id)
    override suspend fun getProjectById(id: Int): ProjectDetailResponse = apiService.getProjectById(id)
    override suspend fun getTasksByProjectId(id: Int): TaskResponse = apiService.getTaskByProjectId(id)
    override suspend fun getTasksDoneByProjectId(id: Int): TaskResponse = apiService.getTaskDoneDByProjectId(id)
    override suspend fun postTeam(
        userId: Int,
        nameTeam: String,
        descriptionTeam: String?,
    ): TeamResponse = apiService.postTeam(userId, nameTeam, descriptionTeam)
    override suspend fun postProject(
        teamId: Int,
        userId: Int,
        nameProject: String,
        descriptionProject: String?,
        dueProject: String,
    ): ProjectResponse = apiService.postProject(teamId, userId, nameProject, descriptionProject, dueProject)
}