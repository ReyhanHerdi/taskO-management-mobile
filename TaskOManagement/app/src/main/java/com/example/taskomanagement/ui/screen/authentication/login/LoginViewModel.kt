package com.example.taskomanagement.ui.screen.authentication.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: MainRepository): ViewModel() {
    private val _auth = MutableStateFlow(false)
    val auth = _auth.asStateFlow()

    private suspend fun setUserLogin() = repository.setUserLogin()
    private suspend fun getUserLogin(): Boolean = repository.getUserLogin()

    private fun updateAuth() {
        viewModelScope.launch {
            _auth.value = getUserLogin()
        }
    }

    private suspend fun setUserId(uid: Int) = repository.setUserId(uid)
    private suspend fun getUserId(): Int = repository.getUserId()

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                val login = repository.login(email, password)
                if (login.status) {
                    setUserLogin()
                    updateAuth()
                    setUserId(login.data)
                } else {
                    Log.d("LOGIN GAGAL", "Email atau password salah")
                }
            } catch (e: Exception) {
                Log.d("LOGIN HAS FAILED", "${e.message}")
            } finally {
                putFcmToken()
            }
        }
    }
    private fun updateFcmToken(
        token: String
    ) {
        viewModelScope.launch {
            try {
                repository.updateFcmToken(
                    getUserId(),
                    token
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun putFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            val token = task.result
            updateFcmToken(token)
        }
    }
}