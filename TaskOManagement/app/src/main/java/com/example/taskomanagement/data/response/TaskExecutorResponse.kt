package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class TaskExecutorResponse(

	@field:SerializedName("data")
	val data: List<TaskByExecutorDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class TaskByExecutorDataItem(

	@field:SerializedName("task")
	val task: List<TaskItem>,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Int
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
