package com.example.taskomanagement.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


class AuthDataStore(private val context: Context) {
    private val authDataStoreKey = "auth_datastore"
    private val uidDataStoreKey = "uid_datastore"
    private val Context.datastore by preferencesDataStore(PREFRENCES_KEY)

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

    suspend fun setUserLogout() {
        val dataStoreKey = booleanPreferencesKey(authDataStoreKey)
        context.datastore.edit {
            it[dataStoreKey] = false
        }
    }

    suspend fun getUserId(): Int {
        val dataStoreKey = intPreferencesKey(uidDataStoreKey)
        val uid = context.datastore.data.first()
        return uid[dataStoreKey] ?: 0
    }

    suspend fun setUserId(uid: Int) {
        val dataStoreKey = intPreferencesKey(uidDataStoreKey)
        context.datastore.edit {
            it[dataStoreKey] = uid
        }
    }

    companion object {
        const val PREFRENCES_KEY = "preferences_key"
    }
}