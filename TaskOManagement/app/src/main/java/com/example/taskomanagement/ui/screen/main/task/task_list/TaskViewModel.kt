package com.example.taskomanagement.ui.screen.main.task.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TaskByExecutorDataItem
import com.example.taskomanagement.data.response.TaskDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: MainRepository) : ViewModel() {
    private val _task = MutableStateFlow<List<TaskByExecutorDataItem>>(emptyList())
    val task = _task.asStateFlow()

    private suspend fun getUserId(): Int = repository.getUserId()

    fun getTaskByExecutor() {
        viewModelScope.launch {
            try {
                val taskData = repository.getTaskByExector(getUserId())
                _task.value = taskData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}