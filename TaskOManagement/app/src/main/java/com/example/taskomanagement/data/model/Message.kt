package com.example.taskomanagement.data.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.Date

@IgnoreExtraProperties
data class Message(
    val senderId: Int? = 0,
    val userName: String? = "",
    val email: String? = "",
    val message: String? = "",
    val currentDate: Long? = 0L
)
