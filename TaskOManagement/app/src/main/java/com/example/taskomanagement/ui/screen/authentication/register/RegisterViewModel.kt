package com.example.taskomanagement.ui.screen.authentication.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.UserResponse
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

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

    fun firebaseAuth(email: String, password: String) {
        val auth = Firebase.auth
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser
                    } else {
                        Log.d("Firebase Registration", "registerWithEmailAndPassword:Failure", task.exception)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}