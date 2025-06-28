package com.example.taskomanagement.ui.screen.main.task.task_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDetailData
import com.example.taskomanagement.data.response.TaskDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(private val repository: MainRepository) : ViewModel() {
    private val _task = MutableStateFlow<TaskDataItem?>(null)
    val task = _task.asStateFlow()

    private val _project = MutableStateFlow<ProjectDetailData?>(null)
    val project = _project.asStateFlow()

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            try {
                val taskData = repository.getTaskById(id)
                _task.value = taskData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getProjectById(id: Int) {
        viewModelScope.launch {
            try {
                val projectData = repository.getProjectById(id)
                _project.value = projectData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}