package com.example.taskomanagement.ui.screen.main.message.message_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.MessageDataItem
import com.example.taskomanagement.data.response.TeamDataItem
import com.example.taskomanagement.data.response.TeamMemberDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MessageListViewModel(private val repository: MainRepository): ViewModel() {
    suspend fun getUserId() = repository.getUserId()
    private val _messages = MutableStateFlow<List<MessageDataItem>>(emptyList())
    val messages = _messages.asStateFlow()
    
    private val _userId = mutableStateOf<Int?>(null)
    val userId: State<Int?> = _userId

    private  val _teamId = mutableStateOf<Int?>(null)
    val teamId: State<Int?> = _teamId

    fun getMessages() {
        viewModelScope.launch {
            try {
                val messagesData = repository.getMessageList(getUserId())
                _messages.value = messagesData.data
                _userId.value = getUserId()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTeam() {
        viewModelScope.launch {
            try {
                val teamData = repository.getTeamByUserId(getUserId())
                _teamId.value = teamData.data[0].teamId
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}