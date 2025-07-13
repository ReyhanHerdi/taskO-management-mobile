package com.example.taskomanagement.ui.screen.main.member

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.MemberOfTeamDataItem
import com.example.taskomanagement.data.response.UserDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MemberViewModel(private val repository: MainRepository) : ViewModel() {
    private val _member = MutableStateFlow<List<MemberOfTeamDataItem?>>(emptyList())
    val member = _member.asStateFlow()

    private val _user =  MutableStateFlow(UserDataItem())
    val user = _user.asStateFlow()

    suspend fun getUserId(): Int = repository.getUserId()

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

    fun getUser() {
        viewModelScope.launch {
            try {
                val uid = repository.getUserId()
                val userData = repository.getUser(uid)
                _user.value = userData.data!!
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                Log.d("USER DATA", _user.toString())
            }
        }
    }
}