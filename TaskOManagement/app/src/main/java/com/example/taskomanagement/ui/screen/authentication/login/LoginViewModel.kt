package com.example.taskomanagement.ui.screen.authentication.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.repository.MainRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.taskomanagement.data.model.Result

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

    private val _loginResult = mutableStateOf<Result<String>?>(null)
    val loginResult: State<Result<String>?> = _loginResult

    private suspend fun setUserId(uid: Int) = repository.setUserId(uid)
    private suspend fun getUserId(): Int = repository.getUserId()

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _loginResult.value = Result.Loading
            try {
                val login = repository.login(email, password)
                if (login.status) {
                    setUserLogin()
                    updateAuth()
                    setUserId(login.data)
                    putFcmToken()
                    _loginResult.value = Result.Success("Login berhasil")
                } else {
                    Log.d("LOGIN GAGAL", "Email atau password salah")
                    _loginResult.value = Result.Error("Email atau password salah")
                }
            } catch (e: Exception) {
                Log.d("LOGIN HAS FAILED", "${e.message}")
                _loginResult.value = Result.Error("${e.message}")
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