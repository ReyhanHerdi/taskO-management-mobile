package com.example.taskomanagement.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NavigationSharedViewModel : ViewModel() {
    var teamId by mutableStateOf<Int?>(null)
    var userName by mutableStateOf<String?>(null)

    fun setTeamId(id: Int) {
        teamId = id
    }

    fun setUerName(name: String) {
        userName = name
    }
}