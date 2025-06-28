package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class TaskDetailResponse(

	@field:SerializedName("data")
	val data: TaskDataItem,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)
