package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class TeamResponse(

	@field:SerializedName("data")
	val data: List<TeamDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class TeamDataItem(

	@field:SerializedName("id_team")
	val idTeam: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("name_team")
	val nameTeam: String
)
