package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class MembersOfTeamResponse(

	@field:SerializedName("data")
	val data: List<MemberOfTeamDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class MemberOfTeamDataItem(

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

	@field:SerializedName("user")
	val user: List<UserItem>
)
