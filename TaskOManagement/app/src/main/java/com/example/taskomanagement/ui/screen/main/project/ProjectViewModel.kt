package com.example.taskomanagement.ui.screen.main.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.MemberItem
import com.example.taskomanagement.data.response.ProjectDataItem
import com.example.taskomanagement.data.response.TeamDataItem
import com.example.taskomanagement.data.response.TeamItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: MainRepository): ViewModel() {
    private suspend fun getUserId() = repository.getUserId()

    private val _project = MutableStateFlow<List<ProjectDataItem>>(emptyList())
    val project = _project.asStateFlow()

    private val _team = MutableStateFlow<List<MemberItem>>(emptyList())
    val team = _team.asStateFlow()

    fun getTeam() {
        viewModelScope.launch {
            try {
                val teamData = repository.getUserTeams(getUserId())
                teamData.data.forEach { user ->
                    user.member.forEach { it ->
                        getProject(it.teamId)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                Log.d("PROJECT BY ITEM", _project.toString())
            }
        }
    }

    private fun getProject(teamId: Int) {
        viewModelScope.launch {
            try {
                val projectData = repository.getProject(teamId)
                _project.value = projectData.data
            } catch (e: Exception) {
                Log.d("FETCHED DATA FAIL", "${e.message}")
            }
        }
    }
}