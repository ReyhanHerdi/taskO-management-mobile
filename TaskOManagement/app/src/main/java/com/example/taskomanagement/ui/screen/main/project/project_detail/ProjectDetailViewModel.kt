package com.example.taskomanagement.ui.screen.main.project.project_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDataItem
import com.example.taskomanagement.data.response.ProjectDetailData
import com.example.taskomanagement.data.response.TeamDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectDetailViewModel(private val repository: MainRepository) : ViewModel() {
    private val _project = MutableStateFlow<ProjectDetailData?>(null)
    val project = _project.asStateFlow()

    private val _team = MutableStateFlow<TeamDataItem?>(null)
    val team = _team.asStateFlow()

    fun getProjectDetail(id: Int) {
        viewModelScope.launch {
            try {
                val projectData = repository.getProjectById(id)
                _project.value = projectData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTeamDetail(id: Int) {
        viewModelScope.launch {
            try {
                val teamData = repository.getTeamByTeamId(id)
                _team.value = teamData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}