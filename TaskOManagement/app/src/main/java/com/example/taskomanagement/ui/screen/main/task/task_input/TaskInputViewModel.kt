package com.example.taskomanagement.ui.screen.main.task.task_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TaskResponse
import kotlinx.coroutines.launch

class TaskInputViewModel(private val repository: MainRepository) : ViewModel() {
    fun postTask(
        projectId: Int,
        nameTask: String,
        descriptionTask: String,
        dueDateTask: String,
        dueTimeTask: String
    ) {
        viewModelScope.launch {
            try {
                repository.postTask(projectId, nameTask, descriptionTask, dueDateTask, dueTimeTask)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}