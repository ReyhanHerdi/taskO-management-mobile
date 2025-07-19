package com.example.taskomanagement.ui.screen.main.project.project_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDetailData
import com.example.taskomanagement.data.response.TaskDataItem
import com.example.taskomanagement.data.response.TeamDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectDetailViewModel(private val repository: MainRepository) : ViewModel() {
    private val _project = MutableStateFlow<ProjectDetailData?>(null)
    val project = _project.asStateFlow()

    private val _team = MutableStateFlow<TeamDataItem?>(null)
    val team = _team.asStateFlow()

    private val _task = MutableStateFlow<List<TaskDataItem>>(emptyList())
    val task = _task.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    fun getProjectDetail(id: Int) {
        viewModelScope.launch {
            try {
                val projectData = repository.getProjectById(id)
                _project.value = projectData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTasks(id: Int) {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val taskData = repository.getTasksByProjectId(id)
                _task.value = taskData.data
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                e.printStackTrace()
                _dataResult.value = Result.Error("Get data fail")
            }
        }
    }
}