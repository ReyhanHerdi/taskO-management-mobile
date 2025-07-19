package com.example.taskomanagement.ui.screen.main.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
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

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    private suspend fun getUserId(): Int = repository.getUserId()

    fun getUser() {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val userData = repository.getUser(getUserId())
                _user.value = "${userData.data?.name}"
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                e.printStackTrace()
                _dataResult.value = Result.Error("Get data fail")
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