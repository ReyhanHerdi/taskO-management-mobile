package com.example.taskomanagement.ui.screen.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDataItem
import com.example.taskomanagement.data.response.TaskByExecutorDataItem
import com.example.taskomanagement.data.response.TaskDataItem
import com.example.taskomanagement.data.response.TeamDataItem
import com.example.taskomanagement.data.response.TeamMemberDataItem
import com.example.taskomanagement.data.response.UserDataItem
import com.example.taskomanagement.data.response.UserItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: MainRepository): ViewModel() {
    private suspend fun setUserLogout() = repository.setUserLogout()
    private suspend fun getUserLogout(): Boolean = repository.getUserLogin()
    private suspend fun setUserId(uid: Int) = repository.setUserId(uid)
    private suspend fun getUserId(): Int = repository.getUserId()

    private val _user =  MutableStateFlow(UserDataItem())
    val user = _user.asStateFlow()

    private val _task = MutableStateFlow<List<TaskByExecutorDataItem>?>(emptyList())
    val task = _task.asStateFlow()

    private val _team = MutableStateFlow<List<TeamMemberDataItem?>>(emptyList())
    val team = _team.asStateFlow()

    private val _project = MutableStateFlow<List<ProjectDataItem?>>(emptyList())
    val project = _project.asStateFlow()

    private val _auth = MutableStateFlow(true)
    val auth = _auth.asStateFlow()

    private fun updateAuth() {
        viewModelScope.launch {
            _auth.value = getUserLogout()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            try {
                val uid = repository.getUserId()
                val userData = repository.getUser(uid)
                if (userData.data != null) {
                    _user.value = userData.data
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                Log.d("USER DATA", _user.toString())
            }
        }
    }

    fun getTask() {
        viewModelScope.launch {
            try {
                val uid = repository.getUserId()
                val taskData = repository.getTaskByExector(uid)
                _task.value = taskData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTeam() {
        viewModelScope.launch {
            try {
                val teamData = repository.getTeamByUserId(getUserId())
                _team.value = teamData.data
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
            try {
                val projectData = repository.getProject(teamId)
                val data = _project.value.toMutableList()
                val idData = data.map { it?.idProject ?: 0 }
                projectData.data.forEach { prodata ->
                    if (!data.contains(prodata)) {
                        data.removeAll { prodata.idProject in idData}
                        data.add(prodata)
                    }
                }
                _project.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                Log.d("PROJECTS", project.value.toString())
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                setUserLogout()
                updateAuth()
                setUserId(0)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                Log.d("USER ID RESET", "uid: ${getUserId()}")
            }
        }
    }
}