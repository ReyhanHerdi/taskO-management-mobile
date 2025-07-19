package com.example.taskomanagement.ui.screen.main.teams.team_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.TeamMemberDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: MainRepository): ViewModel() {
    private val _team = MutableStateFlow<List<TeamMemberDataItem>>(emptyList())
    val team = _team.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    private suspend fun getUserId(): Int = repository.getUserId()

    fun getTeam() {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val member = repository.getTeamByUserId(getUserId())
                _team.value = member.data
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _dataResult.value = Result.Success("Get data fail")
                Log.d("TEAMS", "data: ${team.value}")
            }
        }
    }
}