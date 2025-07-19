package com.example.taskomanagement.ui.screen.main.teams.team_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDataItem
import com.example.taskomanagement.data.response.TeamDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamDetailViewModel(private val repository: MainRepository): ViewModel() {
    private val _team = MutableStateFlow<TeamDataItem?>(null)
    val team = _team.asStateFlow()

    private val _project = MutableStateFlow<List<ProjectDataItem?>>(emptyList())
    val project = _project.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    fun getTeam(id: Int) {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val team = repository.getTeamByTeamId(id)
                _team.value = team.data
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                e.printStackTrace()
                _dataResult.value = Result.Error("Get data fail")
            }
        }
    }

    fun getProjects(id: Int) {
        viewModelScope.launch {
            try {
                val project = repository.getProjectsByTeamId(id)
                _project.value = project.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}