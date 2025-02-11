package com.example.taskomanagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskomanagement.data.repository.Repository
import com.example.taskomanagement.ui.screen.main.teams.TeamViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass) {
        TeamViewModel::class.java -> TeamViewModel(repository) as T
        else -> throw IllegalArgumentException("Unknown model class: " + modelClass.name)
    }
}