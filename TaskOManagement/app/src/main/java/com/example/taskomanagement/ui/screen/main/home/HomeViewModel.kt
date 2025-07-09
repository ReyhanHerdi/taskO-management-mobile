package com.example.taskomanagement.ui.screen.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TaskByExecutorDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository): ViewModel() {
    private val _user = MutableStateFlow("name")
    val user = _user.asStateFlow()

    private val _task = MutableStateFlow<List<TaskByExecutorDataItem>?>(emptyList())
    val task = _task.asStateFlow()

    private suspend fun getUserId(): Int = repository.getUserId()

    fun getUser() {
        viewModelScope.launch {
            try {
                val userData = repository.getUser(getUserId())
                _user.value = "${userData.data?.name}"
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTask() {
        viewModelScope.launch {
            try {
                val taskData = repository.getTaskByExector(getUserId())
                val taskExecutor = taskData.data
                _task.value = taskExecutor
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getUserLogin() = repository.getUserLogin()
}