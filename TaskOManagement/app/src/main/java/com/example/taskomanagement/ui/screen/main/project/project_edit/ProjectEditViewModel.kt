package com.example.taskomanagement.ui.screen.main.project.project_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDetailData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectEditViewModel(private val repository: MainRepository) : ViewModel() {
    private val _project = MutableStateFlow<ProjectDetailData?>(null)
    val project = _project.asStateFlow()

    private val _loadingState = mutableStateOf<Result<String>?>(null)
    val loadingStat: State<Result<String>?> = _loadingState

    fun getProject(projectId: Int) {
        viewModelScope.launch {
            _loadingState.value = Result.Loading
            try {
                val projectData = repository.getProjectById(projectId)
                _project.value = projectData.data
                _loadingState.value = Result.Success("Get data success")
            } catch (e: Exception) {
                _loadingState.value = Result.Error("Get data fail")
                e.printStackTrace()
            }
        }
    }

    fun updateProject(
        projectId: Int,
        projectName: String,
        projectDescription: String,
        projectDue: String
    ) {
        viewModelScope.launch {
            _loadingState.value = Result.Loading
            try {
                repository.updateProject(
                    projectId, projectName, projectDescription, projectDue
                )
                _loadingState.value = Result.Success("Update data success")
            } catch (e: Exception) {
                _loadingState.value = Result.Error("Update data fail")
                e.printStackTrace()
            }
        }
    }
}