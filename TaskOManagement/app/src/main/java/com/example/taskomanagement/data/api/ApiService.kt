package com.example.taskomanagement.data.api

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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/user/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ) : UserResponse

    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("api/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): UserResponse

    @GET("api/teams")
    suspend fun getTeams(): TeamResponse

    @FormUrlEncoded
    @POST("api/teams")
    suspend fun postTeam(
        @Field("user_id") userId: Int,
        @Field("name_team") nameTeam: String,
        @Field("description") descriptionTeam: String? = null
    ): TeamResponse

    @GET("api/user-teams/{id}")
    suspend fun getUserTeams(
        @Path("id") id: Int
    ): TeamOfUserResponse

    @GET("api/projects-team/{id}")
    suspend fun getProjects(
        @Path("id") id: Int
    ): ProjectWithTaskResponse

    @GET("api/tasks")
    suspend fun getTasks(): TaskResponse

    @GET("api/task/{id}")
    suspend fun getTaskById(
        @Path("id") id: Int
    ): TaskDetailResponse

    @GET("api/task-executor/{id}")
    suspend fun getTaskByExecutor(
        @Path("id") id: Int
    ): TaskExecutorResponse

    @GET("api/user-teams/{id}")
    suspend fun getTeamsByUserId(
        @Path("id") id: Int
    ): TeamMemberResponse

    @GET("api/team/{id}")
    suspend fun getTeamById(
        @Path("id") id: Int
    ): TeamResponse

    @GET("api/projects-team/{id}")
    suspend fun getProjectsByTeamId(
        @Path("id") id: Int
    ): ProjectResponse

    @GET("api/project/{id}")
    suspend fun getProjectById(
        @Path("id") id: Int
    ): ProjectDetailResponse

    @FormUrlEncoded
    @POST("api/projects")
    suspend fun postProject(
        @Field("team_id") teamId: Int,
        @Field("user_id") userId: Int,
        @Field("name_project") nameProject: String,
        @Field("description") descriptionProject: String? = null,
        @Field("due") dueProject: String
    ): ProjectResponse

    @GET("api/tasks-project/{id}")
    suspend fun getTaskByProjectId(
        @Path("id") id: Int
    ): TaskResponse

    @GET("api/tasks-done-project/{id}")
    suspend fun getTaskDoneDByProjectId(
        @Path("id") id: Int
    ): TaskResponse
}