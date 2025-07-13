package com.example.taskomanagement.ui.screen.main.message.message_chat

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Message
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.UserDataItem
import com.example.taskomanagement.utils.currentDateTime
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MessageViewModel(private val repository: MainRepository) : ViewModel() {
    private val _user =  MutableStateFlow(UserDataItem())
    val user = _user.asStateFlow()
    private var memberId by mutableStateOf<Int?>(null)
    private val db: FirebaseDatabase = Firebase.database
    val messages = mutableStateListOf<Message>()

    private suspend fun getUserId(): Int = repository.getUserId()
    fun setMemberId(id: Int) {
        memberId = id
    }
    private suspend fun refChild(): String = "${memberId}_${getUserId()}"
    private suspend fun messageRef(): DatabaseReference = db.reference.child(refChild())
    private suspend fun myRef(): DatabaseReference = db.getReference(refChild())

    fun sendMessage(
        text: String
    ) {
        viewModelScope.launch {
            getUser()
            try {
                val message = Message(
                    senderId = getUserId(),
                    userName = user.value.name ?: "Tanpa nama",
                    email = user.value.email ?: "Tanpa email",
                    message = text,
                    currentDate = currentDateTime().time
                )
                messageRef().push().setValue(message)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun getUser() {
        try {
            val uid = repository.getUserId()
            val userData = repository.getUser(uid)
            if (userData.data != null) {
                _user.value = userData.data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            Log.d("USER DATA", _user.toString())
        }
    }

    fun observeMessage() {
        viewModelScope.launch {
            messageRef().addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messages.clear()
                    for (child in snapshot.children) {
                        val msg = child.getValue(Message::class.java)
                        msg?.let { messages.add(it) }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("DB", "Database error: ${error.message}")
                }
            })
        }
    }
}