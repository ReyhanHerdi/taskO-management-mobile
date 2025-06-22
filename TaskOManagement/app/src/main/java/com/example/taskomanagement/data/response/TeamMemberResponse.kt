package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class TeamMemberResponse(

	@field:SerializedName("data")
	val data: List<TeamMemberDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class TeamMemberDataItem(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("team_id")
	val teamId: Int,

	@field:SerializedName("team")
	val team: List<TeamDataItem>
)
