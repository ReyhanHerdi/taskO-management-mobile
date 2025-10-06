package com.example.taskomanagement.data.response

import com.google.gson.annotations.SerializedName

data class MessageResponse(

	@field:SerializedName("data")
	val data: List<MessageDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class MessageDataItem(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id_message")
	val idMessage: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("last_message")
	val lastMessage: String,

	@field:SerializedName("team_id")
	val teamId: Int,

	@field:SerializedName("user2_id")
	val user2Id: Int,

	@field:SerializedName("name")
	val name1: String,

	@field:SerializedName("name_2")
	val name2: String,

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("photoUrl_2")
	val photo2Url: String? = null,
)
