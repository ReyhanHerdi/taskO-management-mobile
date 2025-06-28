package com.example.taskomanagement.ui.screen.main.teams.team_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import kotlinx.coroutines.launch

class TeamInputViewModel(private val repository: MainRepository) : ViewModel() {
    private suspend fun getUserId(): Int = repository.getUserId()

    fun postTeam(
        teamName: String,
        teamDescription: String? = null
    ) {
        viewModelScope.launch {
            try {
                repository.postTeam(
                    getUserId(),
                    teamName,
                    teamDescription
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}