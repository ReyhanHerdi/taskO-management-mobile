package com.example.taskomanagement.ui.screen.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TaskDataItem
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository): ViewModel() {
    private val _task = MutableLiveData<List<TaskDataItem>>()
    val task: LiveData<List<TaskDataItem>> get() = _task

    fun getTask() {
        viewModelScope.launch {
            try {
                val taskData = repository.getTask()
                _task.value = taskData.data
            } catch (e: Exception) {
                Log.d("FETCHED DATA FAIL", "${e.message}")
            }
        }
    }
}