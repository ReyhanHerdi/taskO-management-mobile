package com.example.taskomanagement

import android.app.Application
import com.example.taskomanagement.data.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TaskoApplication)
            modules(appModule)
        }
    }
}