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
