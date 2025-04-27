package com.example.taskomanagement.ui.screen.main.profile

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TaskDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: MainRepository): ViewModel() {
    private val _task = MutableLiveData<List<TaskDataItem>>()
    val task: LiveData<List<TaskDataItem>> get() = _task

    private suspend fun setUserLogout() = repository.setUserLogout()
    private suspend fun getUserLogout(): Boolean = repository.getUserLogin()

    private val _auth = MutableStateFlow(true)
    val auth = _auth.asStateFlow()

    private fun updateAuth() {
        viewModelScope.launch {
            _auth.value = getUserLogout()
        }
    }


    fun getTask() {
        viewModelScope.launch {
            try {
                val taskData = repository.getTask()
                _task.value = taskData.data
            } catch (e: Exception) {
                Log.d("FETCHING DATA HAS FAILED", "${e.message}")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                setUserLogout()
                updateAuth()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}