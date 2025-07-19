package com.example.taskomanagement.ui.screen.main.project.project_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDataItem
import com.example.taskomanagement.data.response.TeamMemberDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: MainRepository): ViewModel() {
    private suspend fun getUserId() = repository.getUserId()

    private val _project = MutableStateFlow<List<ProjectDataItem>>(emptyList())
    val project = _project.asStateFlow()

    private val _team = MutableStateFlow<List<TeamMemberDataItem>>(emptyList())
    val team = _team.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    fun getTeam() {
        viewModelScope.launch {
            try {
                val teamData = repository.getTeamByUserId(getUserId())
                teamData.data.forEach {
                    getProject(it.teamId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getProject(teamId: Int) {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val projectData = repository.getProject(teamId)
                val data = _project.value.toMutableList()
                val idData = data.map { it.idProject }
                projectData.data.forEach { prodata ->
                    if (!data.contains(prodata)) {
                        data.removeAll { prodata.idProject in idData}
                        data.add(prodata)
                    }
                }
                _project.value = data
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                _dataResult.value = Result.Error("Get data fail")
                e.printStackTrace()
            }
        }
    }
}