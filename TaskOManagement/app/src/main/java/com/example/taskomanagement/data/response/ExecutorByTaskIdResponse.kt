package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class ExecutorByTaskIdResponse(

	@field:SerializedName("data")
	val data: List<ExecutorByTaskIdDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class ExecutorByTaskIdDataItem(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Int,

	@field:SerializedName("user")
	val user: List<UserItem>
)
