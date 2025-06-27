package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class ProjectWithTaskResponse(

	@field:SerializedName("data")
	val data: List<ProjectDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataItem(

	@field:SerializedName("task")
	val task: List<TaskDataItem>,

	@field:SerializedName("name_project")
	val nameProject: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("due")
	val due: String,

	@field:SerializedName("description")
	val description: Any,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("team_id")
	val teamId: Int,

	@field:SerializedName("id_project")
	val idProject: Int,

	@field:SerializedName("status")
	val status: String
)

data class TaskItem(

	@field:SerializedName("name_task")
	val nameTask: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("project_id")
	val projectId: Int,

	@field:SerializedName("due")
	val due: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id_task")
	val idTask: Int,

	@field:SerializedName("status")
	val status: String
)
