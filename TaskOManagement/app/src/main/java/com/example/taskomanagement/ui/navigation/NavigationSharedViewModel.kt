package com.example.taskomanagement.ui.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NavigationSharedViewModel : ViewModel() {
    var teamId by mutableStateOf<Int?>(null)
    var projectId by mutableStateOf<Int?>(null)
    var taskId by mutableStateOf<Int?>(null)
    var userName by mutableStateOf<String?>(null)

    fun setTeamId(id: Int) {
        teamId = id
    }

    fun setProjectId(id: Int) {
        projectId = id
    }

    fun setTaskId(id: Int) {
        taskId = id
    }

    fun setUerName(name: String) {
        Log.d("Name", name)
        userName = name
        Log.d("USER NAME", userName ?: "kosong")
    }
}