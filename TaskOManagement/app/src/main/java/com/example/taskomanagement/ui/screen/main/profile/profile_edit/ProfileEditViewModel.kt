package com.example.taskomanagement.ui.screen.main.profile.profile_edit

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.UserDataItem
import com.example.taskomanagement.utils.uploadFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class ProfileEditViewModel(private val repository: MainRepository) : ViewModel() {
    suspend fun getUserId(): Int = repository.getUserId()

    private val _user =  MutableStateFlow(UserDataItem())
    val user = _user.asStateFlow()

    private val _name = mutableStateOf<String?>(null)
    val name: State<String?> = _name

    private val _image = MutableStateFlow("uri")
    val image = _image.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    fun getUser() {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            val userId = getUserId()
            try {
                val userData = repository.getUser(userId)
                if (userData.data != null) {
                    _user.value = userData.data
                }
                _name.value = user.value.name
                _dataResult.value = Result.Success("Get data success")
            } catch (e: Exception) {
                _dataResult.value = Result.Error("Get data fail")
                e.printStackTrace()
            }
        }
    }

    fun updateUser(
        userName: String,
        userPhotoUrl: String? = null
    ) {
        viewModelScope.launch {
            _dataResult.value = Result.Loading
            try {
                val userId = getUserId()
                repository.updateUser(
                    userId, userName, userPhotoUrl
                )
                _dataResult.value = Result.Success("Update data success")
            } catch (e: Exception) {
                _dataResult.value = Result.Error("Update data fail")
                e.printStackTrace()
            }
        }
    }

    fun updateImage(uri: String) {
        _image.value = uri
    }

    fun inputImage(
        uri: Uri,
        context: Context
    ) {
        try {
            val file = uploadFile(uri, context)
            uploadImage(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun uploadImage(
        file: File
    ) {
        viewModelScope.launch {
            val userId = getUserId()
            repository.uploadUserImage(userId, file)
        }
    }
}