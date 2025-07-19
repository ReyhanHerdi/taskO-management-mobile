package com.example.taskomanagement.ui.screen.main.task.task_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TaskByExecutorDataItem
import com.example.taskomanagement.data.response.TaskDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: MainRepository) : ViewModel() {
    private val _task = MutableStateFlow<List<TaskByExecutorDataItem>>(emptyList())
    val task = _task.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    private suspend fun getUserId(): Int = repository.getUserId()

    fun getTaskByExecutor() {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val taskData = repository.getTaskByExector(getUserId())
                _task.value = taskData.data
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                _dataResult.value = Result.Error("Get data fail")
                e.printStackTrace()
            }
        }
    }
}