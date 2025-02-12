package com.example.taskomanagement.ui.screen.authentication.register

import androidx.lifecycle.ViewModel
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.UserResponse

class RegisterViewModel(private val repository: MainRepository): ViewModel() {
    suspend fun register(
        name: String,
        email: String,
        password: String,
    ): UserResponse = repository.register(name, email, password)

    fun registerValidation(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Boolean = password == passwordConfirmation
            && name.isNotEmpty()
            && email.isNotEmpty()
            && password.isNotEmpty()
            && password.length > 7
}