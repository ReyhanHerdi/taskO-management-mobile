package com.example.taskomanagement.data.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.Date

@IgnoreExtraProperties
data class Message(
    val senderId: Int? = 0,
    val receiverId: Int? = 0,
    val text: String? = "",
    val timestamp: Long? = 0L
)
