package com.example.taskomanagement.ui.screen.main.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ProjectDataItem
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: MainRepository): ViewModel() {
    private val _project = MutableLiveData<List<ProjectDataItem>>()
    val project: LiveData<List<ProjectDataItem>> get() = _project

    fun getProject() {
        viewModelScope.launch {
            try {
                val projectData = repository.getProject()
                _project.value = projectData.data
            } catch (e: Exception) {
                Log.d("FETCHED DATA FAIL", "${e.message}")
            }
        }
    }
}