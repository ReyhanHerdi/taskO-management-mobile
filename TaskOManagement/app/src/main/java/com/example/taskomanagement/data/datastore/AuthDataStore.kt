package com.example.taskomanagement.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


class AuthDataStore(private val context: Context) {
    private val authDataStoreKey = "auth_datastore"
    private val Context.datastore by preferencesDataStore(authDataStoreKey)

    suspend fun setUserLogin() {
        val dataStoreKey = booleanPreferencesKey(authDataStoreKey)
        context.datastore.edit {
            it[dataStoreKey] = true
        }
    }

    suspend fun getUserLogin(): Boolean {
        val dataStoreKey = booleanPreferencesKey(authDataStoreKey)
        val status = context.datastore.data.first()
        return status[dataStoreKey] ?: false
    }

    private object PreferencesKey {
        val USER_LOGIN = booleanPreferencesKey("user_login")
    }
}