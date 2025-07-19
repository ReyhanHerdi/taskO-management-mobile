package com.example.taskomanagement.ui.screen.main.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDataItem
import com.example.taskomanagement.data.response.TaskByExecutorDataItem
import com.example.taskomanagement.data.response.TeamMemberDataItem
import com.example.taskomanagement.data.response.UserDataItem
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

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    private val _auth = MutableStateFlow(true)
    val auth = _auth.asStateFlow()

    private fun updateAuth() {
        viewModelScope.launch {
            _auth.value = getUserLogout()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val uid = repository.getUserId()
                val userData = repository.getUser(uid)
                if (userData.data != null) {
                    _user.value = userData.data
                }
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                _dataResult.value = Result.Error("Get data fail")
                e.printStackTrace()
            }
        }
    }

    fun getTask() {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val uid = repository.getUserId()
                val taskData = repository.getTaskByExector(uid)
                _task.value = taskData.data
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                e.printStackTrace()
                _dataResult.value = Result.Error("Get data fail")
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
            _dataResult.value = Result.Loading
            try {
                setUserLogout()
                updateAuth()
                setUserId(0)
                _dataResult.value = Result.Success("Logout success")
            } catch (e: Exception) {
                e.printStackTrace()
                _dataResult.value = Result.Error("Logout fail")
            }
        }
    }
}