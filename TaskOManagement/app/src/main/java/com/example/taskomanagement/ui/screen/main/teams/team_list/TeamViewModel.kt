package com.example.taskomanagement.ui.screen.main.teams.team_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TeamMemberDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: MainRepository): ViewModel() {
    private val _team = MutableStateFlow<List<TeamMemberDataItem>>(emptyList())
    val team = _team.asStateFlow()

    private suspend fun getUserId(): Int = repository.getUserId()

    fun getTeam() {
        viewModelScope.launch {
            try {
                val member = repository.getTeamByUserId(getUserId())
                _team.value = member.data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                Log.d("TEAMS", "data: ${team.value}")
            }
        }
    }
}