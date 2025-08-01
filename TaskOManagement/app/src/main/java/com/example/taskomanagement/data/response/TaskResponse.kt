package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class TaskResponse(

	@field:SerializedName("data")
	val data: List<TaskDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class TaskDataItem(

	@field:SerializedName("name_task")
	val nameTask: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("project_id")
	val projectId: Int,

	@field:SerializedName("due_date")
	val dueDate: String,

	@field:SerializedName("due_time")
	val dueTime: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id_task")
	val idTask: Int,

	@field:SerializedName("status")
	val status: String
)
