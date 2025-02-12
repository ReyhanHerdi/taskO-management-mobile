package com.example.taskomanagement.data.api

import com.example.taskomanagement.data.response.ProjectResponse
import com.example.taskomanagement.data.response.TaskResponse
import com.example.taskomanagement.data.response.TeamResponse
import com.example.taskomanagement.data.response.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/users")
    suspend fun getUsers() : UserResponse

    @FormUrlEncoded
    @POST("api/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): UserResponse

    @GET("api/teams")
    suspend fun getTeams(): TeamResponse

    @GET("api/projects")
    suspend fun getProjects(): ProjectResponse

    @GET("api/tasks")
    suspend fun getTasks(): TaskResponse
}