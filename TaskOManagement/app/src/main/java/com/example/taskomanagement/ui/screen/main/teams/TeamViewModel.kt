package com.example.taskomanagement.ui.screen.main.teams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TeamDataItem
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: MainRepository): ViewModel() {
    private val _team = MutableLiveData<List<TeamDataItem>>()
    val team: LiveData<List<TeamDataItem>> get() = _team

    fun getTeam() {
        viewModelScope.launch {
            try {
                val teamData = repository.getTeam()
                _team.value = teamData.data
            } catch (e: Exception) {
                Log.d("ERROR", "${e.message}")
            }
        }
    }
}