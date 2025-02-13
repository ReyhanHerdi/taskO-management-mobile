package com.example.taskomanagement.ui.screen.authentication.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: MainRepository): ViewModel() {
    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                if (repository.login(email, password).status) {
                    setUserLogin()
                } else {
                    Log.d("LOGIN GAGAL", "Email atau password salah")
                }
            } catch (e: Exception) {
                Log.d("LOGIN HAS FAILED", "${e.message}")
            }
        }
    }
    private suspend fun setUserLogin() = repository.setUserLogin()
    suspend fun getUserLogin(): Boolean = repository.getUserLogin()
}