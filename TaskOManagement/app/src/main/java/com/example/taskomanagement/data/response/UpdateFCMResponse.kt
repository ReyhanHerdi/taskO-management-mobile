package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class UpdateFCMResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)
