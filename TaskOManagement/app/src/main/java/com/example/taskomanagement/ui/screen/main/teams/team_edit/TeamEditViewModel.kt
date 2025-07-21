package com.example.taskomanagement.ui.screen.main.teams.team_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TeamDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamEditViewModel(private val repository: MainRepository) : ViewModel() {
    private val _team = MutableStateFlow<TeamDataItem?>(null)
    val team = _team.asStateFlow()

    private val _loadingState = mutableStateOf<Result<String>?>(null)
    val loadingState: State<Result<String>?> = _loadingState

    fun getTeam(teamId: Int) {
        viewModelScope.launch {
            _loadingState.value = Result.Loading
            try {
                val teamData = repository.getTeamByTeamId(teamId)
                _team.value = teamData.data
                _loadingState.value = Result.Success("Get data succes")
            } catch (e: Exception) {
                _loadingState.value = Result.Success("Get data fail")
                e.printStackTrace()
            }
        }
    }

    fun updateTeam(
        teamId: Int,
        teamName: String,
        teamDescription: String
    ) {
        _loadingState.value = Result.Loading
        viewModelScope.launch {
            try {
                repository.updateTeam(
                    teamId, teamName, teamDescription
                )
                _loadingState.value = Result.Success("Update data success")
            } catch (e: Exception) {
                _loadingState.value = Result.Error("Update data fail")
                e.printStackTrace()
            }
        }
    }
}