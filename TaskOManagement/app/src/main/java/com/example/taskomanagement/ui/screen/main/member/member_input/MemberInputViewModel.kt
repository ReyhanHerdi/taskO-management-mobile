package com.example.taskomanagement.ui.screen.main.member.member_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import kotlinx.coroutines.launch

class MemberInputViewModel(private val repository: MainRepository) : ViewModel() {
    fun inputMember(
        teamId: Int,
        email: String,
    ) {
        viewModelScope.launch {
            try {
                repository.inputMember(
                    teamId,
                    email
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}