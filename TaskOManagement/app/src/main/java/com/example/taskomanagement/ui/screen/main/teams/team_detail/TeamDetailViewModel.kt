package com.example.taskomanagement.ui.screen.main.teams.team_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TeamDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamDetailViewModel(private val repository: MainRepository): ViewModel() {
    private val _team = MutableStateFlow<TeamDataItem?>(null)
    val team = _team.asStateFlow()

    fun getTeam(id: Int) {
        viewModelScope.launch {
            try {
                val team = repository.getTeamByTeamId(id)
                _team.value = team.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}