package com.example.taskomanagement.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TeamSharedViewModel : ViewModel() {
    var teamId by mutableStateOf<Int?>(null)

    fun setTeamId(id: Int) {
        teamId = id
    }
}