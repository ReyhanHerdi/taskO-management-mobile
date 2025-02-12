package com.example.taskomanagement.data.module

import com.example.taskomanagement.baseUrl
import com.example.taskomanagement.data.api.ApiService
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.repository.MainRepositoryImpl
import com.example.taskomanagement.ui.screen.main.home.HomeViewModel
import com.example.taskomanagement.ui.screen.main.profile.ProfileViewModel
import com.example.taskomanagement.ui.screen.main.project.ProjectViewModel
import com.example.taskomanagement.ui.screen.main.teams.TeamViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
    viewModel { HomeViewModel(get()) }
    viewModel { TeamViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

}