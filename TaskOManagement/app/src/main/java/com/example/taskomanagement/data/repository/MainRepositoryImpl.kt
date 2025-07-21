package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.api.ApiService
import com.example.taskomanagement.data.datastore.AuthDataStore
import com.example.taskomanagement.data.response.ExecutorByTaskIdResponse
import com.example.taskomanagement.data.response.LoginResponse
import com.example.taskomanagement.data.response.MembersOfTeamResponse
import com.example.taskomanagement.data.response.ProjectDetailResponse
import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.ProjectWithTaskResponse
import com.example.taskomanagement.data.response.TaskDetailResponse
import com.example.taskomanagement.data.response.TaskExecutorResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamMemberResponse
import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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
    override suspend fun getTeamMemberByTeamId(id: Int): MembersOfTeamResponse = apiService.getTeamWithMemberByTeamId(id)
    override suspend fun getProject(teamId: Int): ProjectWithTaskResponse = apiService.getProjects(teamId)
    override suspend fun getTask(): TaskResponse = apiService.getTasks()
    override suspend fun getTaskById(id: Int): TaskDetailResponse = apiService.getTaskById(id)
    override suspend fun getTaskByExector(id: Int): TaskExecutorResponse = apiService.getTaskByExecutor(id)
    override suspend fun getExecutorByTaskId(id: Int): ExecutorByTaskIdResponse = apiService.getExecutorByTaskId(id)
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

    override suspend fun postTask(
        projectId: Int,
        nameTask: String,
        descriptionTask: String?,
        dueDateTask: String,
        dueTimeTask: String,
    ): TaskResponse = apiService.postTask(projectId, nameTask, descriptionTask, dueDateTask, dueTimeTask)

    override suspend fun updateTask(
        taskId: Int,
        nameTask: String?,
        descriptionTask: String?,
        dueDateTask: String?,
        dueTimeTask: String?,
        status: String?
    ): TaskResponse = apiService.updateTask(taskId, nameTask, descriptionTask, dueDateTask, dueTimeTask, status)

    override suspend fun updateFcmToken(
        userId: Int,
        token: String
    ): UserResponse = apiService.updateFCMToken(userId, token)

    override suspend fun sendMessage(
        userId: Int,
        memberId: Int,
        text: String,
        time: Long,
    ): UserResponse = apiService.sendMessage(userId, memberId, text, time)

    override suspend fun inputMember(
        teamId: Int,
        email: String,
    ): MembersOfTeamResponse = apiService.inputMember(teamId, teamId, email)

    override suspend fun updateUser(
        userId: Int,
        userName: String,
        userPhotoUrl: String?,
    ): UserResponse = apiService.updateUser(userId, userName, userPhotoUrl)

    override suspend fun uploadUserImage(
        userId: Int,
        file: File
    ): UserResponse {
        return apiService.uploadUserImage(
            userId = userId,
            file = MultipartBody.Part.createFormData(
                name = "image",
                filename = file.name,
                body = file.asRequestBody("image/*".toMediaTypeOrNull())
            )
        )
    }
}