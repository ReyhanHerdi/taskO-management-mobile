package com.example.taskomanagement.ui.screen.main.task.task_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ExecutorByTaskIdDataItem
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

    private val _executor = MutableStateFlow<List<ExecutorByTaskIdDataItem>?>(emptyList())
    val executor = _executor.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    fun getTaskById(id: Int) {
        _dataResult.value = Result.Loading
        viewModelScope.launch {
            try {
                val taskData = repository.getTaskById(id)
                _task.value = taskData.data
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                e.printStackTrace()
                _dataResult.value = Result.Error("Get data fail")
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

    fun getExecutorByTaskId(id: Int) {
        viewModelScope.launch {
            try {
                val executorData = repository.getExecutorByTaskId(id)
                _executor.value = executorData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setTaskDone(
        taskId: Int,
        status: String
    ) {
        viewModelScope.launch {
            try {
                repository.updateTask(
                    taskId,
                    status = status
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}