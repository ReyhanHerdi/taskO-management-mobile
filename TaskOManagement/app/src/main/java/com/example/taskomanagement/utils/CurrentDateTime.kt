package com.example.taskomanagement.utils

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.util.Date
import java.util.Locale


fun currentDate(taskDate: String, taskTime: String): Date {
    val taskDateTime = "$taskDate $taskTime"
    Log.d("DATE TIME", taskDateTime)
    return formatDateTimetoTimeinMills(taskDateTime)
}

fun formatDateTimetoTimeinMills(dateTime: String): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.parse(dateTime)
}