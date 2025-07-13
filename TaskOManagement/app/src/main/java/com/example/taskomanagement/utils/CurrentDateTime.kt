package com.example.taskomanagement.utils

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.util.Calendar
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

fun formatDate(date: String): String {
    val stringToDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateAsDate = stringToDate.parse(date)
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return formatter.format(dateAsDate)
}

fun currentDateTime(): Date {
    val currentDate = Calendar.getInstance()
    return currentDate.time
}

fun convertMillisToDate(millis: Long?): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(Date(millis ?: 1055178000000L))
}

fun convertMillisToTime(millis: Long?): String {
    val formatter = SimpleDateFormat("hh:mm", Locale.getDefault())
    return formatter.format(Date(millis ?: 1055178000000L))
}