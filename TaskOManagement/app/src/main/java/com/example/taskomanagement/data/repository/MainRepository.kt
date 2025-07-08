package com.example.taskomanagement.data.repository

import com.example.taskomanagement.data.response.LoginResponse
import com.example.taskomanagement.data.response.MembersOfTeamResponse
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
import okhttp3.internal.concurrent.Task

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
    suspend fun getTeamMemberByTeamId(id: Int): MembersOfTeamResponse
    suspend fun getProject(teamId: Int): ProjectWithTaskResponse
    suspend fun getTask(): TaskResponse
    suspend fun getTaskById(id: Int): TaskDetailResponse
    suspend fun getTaskByExector(id: Int): TaskExecutorResponse
    suspend fun getTeamByUserId(id: Int): TeamMemberResponse
    suspend fun getTeamByTeamId(id: Int): TeamResponse
    suspend fun getProjectsByTeamId(id: Int): ProjectResponse
    suspend fun getProjectById(id: Int): ProjectDetailResponse
    suspend fun getTasksByProjectId(id: Int): TaskResponse
    suspend fun getTasksDoneByProjectId(id: Int): TaskResponse
    suspend fun postTeam(
        userId: Int,
        nameTeam: String,
        descriptionTeam: String? = null
    ): TeamResponse
    suspend fun postProject(
        teamId: Int,
        userId: Int,
        nameProject: String,
        descriptionProject: String? = null,
        dueProject: String
    ): ProjectResponse
    suspend fun postTask(
        projectId: Int,
        nameTask: String,
        descriptionTask: String? = null,
        dueDateTask: String,
        dueTimeTask: String
    ): TaskResponse
}