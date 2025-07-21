package com.example.taskomanagement.ui.screen.main.task.task_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TaskDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.taskomanagement.data.model.Result

class TaskEditViewModel(private val repository: MainRepository) : ViewModel() {
    private val _task = MutableStateFlow<TaskDataItem?>(null)
    val task = _task.asStateFlow()

    private val _loadingState = mutableStateOf<Result<String>?>(null)
    val loadingState: State<Result<String>?> = _loadingState

    fun getTask(taskId: Int) {
        viewModelScope.launch {
            _loadingState.value = Result.Loading
            try {
                val taskData = repository.getTaskById(taskId)
                _task.value = taskData.data
                _loadingState.value = Result.Success("Get data success")
            } catch (e: Exception) {
                _loadingState.value = Result.Error("Get data fail")
                e.printStackTrace()
            }
        }
    }

    fun updateTask(
        taskId: Int,
        taskName: String,
        taskDescription: String,
        taskDueDate: String,
        taskDueTime: String,
        status: String
    ) {
        viewModelScope.launch {
            _loadingState.value = Result.Loading
            try {
                repository.updateTask(
                    taskId, taskName, taskDescription, taskDueDate, taskDueTime, status
                )
                _loadingState.value = Result.Success("Update data success")
            } catch (e: Exception) {
                _loadingState.value = Result.Error("Update data fail")
                e.printStackTrace()
            }
        }
    }
}