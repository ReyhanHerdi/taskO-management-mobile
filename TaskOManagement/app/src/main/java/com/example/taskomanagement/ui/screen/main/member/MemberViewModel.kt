package com.example.taskomanagement.ui.screen.main.member

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.MemberItem
import com.example.taskomanagement.data.response.MemberOfTeamDataItem
import com.example.taskomanagement.data.response.UserDataItem
import com.example.taskomanagement.data.response.UserItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MemberViewModel(private val repository: MainRepository) : ViewModel() {
    private val _member = MutableStateFlow<List<MemberOfTeamDataItem?>>(emptyList())
    val member = _member.asStateFlow()

    fun getMember(teamId: Int) {
        viewModelScope.launch {
            try {
                val memberData = repository.getTeamMemberByTeamId(teamId)
                _member.value = memberData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}