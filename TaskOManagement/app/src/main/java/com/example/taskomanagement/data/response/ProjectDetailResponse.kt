package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class ProjectDetailResponse(

	@field:SerializedName("data")
	val data: ProjectDetailData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class ProjectDetailData(

	@field:SerializedName("name_project")
	val nameProject: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("due")
	val due: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("team_id")
	val teamId: Int,

	@field:SerializedName("id_project")
	val idProject: Int,

	@field:SerializedName("status")
	val status: String
)
