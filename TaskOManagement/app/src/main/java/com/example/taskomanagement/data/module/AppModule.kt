package com.example.taskomanagement.data.module

import com.example.taskomanagement.baseUrl
import com.example.taskomanagement.data.api.ApiService
import com.example.taskomanagement.data.datastore.AuthDataStore
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.repository.MainRepositoryImpl
import com.example.taskomanagement.ui.navigation.NavigationSharedViewModel
import com.example.taskomanagement.ui.screen.authentication.login.LoginViewModel
import com.example.taskomanagement.ui.screen.authentication.register.RegisterViewModel
import com.example.taskomanagement.ui.screen.main.home.HomeViewModel
import com.example.taskomanagement.ui.screen.main.member.member_input.MemberInputViewModel
import com.example.taskomanagement.ui.screen.main.member.member_list.MemberViewModel
import com.example.taskomanagement.ui.screen.main.message.message_chat.MessageViewModel
import com.example.taskomanagement.ui.screen.main.message.message_list.MessageListViewModel
import com.example.taskomanagement.ui.screen.main.profile.ProfileViewModel
import com.example.taskomanagement.ui.screen.main.profile.profile_edit.ProfileEditViewModel
import com.example.taskomanagement.ui.screen.main.project.project_list.ProjectViewModel
import com.example.taskomanagement.ui.screen.main.project.project_detail.ProjectDetailViewModel
import com.example.taskomanagement.ui.screen.main.project.project_edit.ProjectEditViewModel
import com.example.taskomanagement.ui.screen.main.project.project_input.ProjectInputViewModel
import com.example.taskomanagement.ui.screen.main.task.task_list.TaskViewModel
import com.example.taskomanagement.ui.screen.main.task.task_detail.TaskDetailViewModel
import com.example.taskomanagement.ui.screen.main.task.task_edit.TaskEditViewModel
import com.example.taskomanagement.ui.screen.main.task.task_input.TaskInputViewModel
import com.example.taskomanagement.ui.screen.main.teams.team_list.TeamViewModel
import com.example.taskomanagement.ui.screen.main.teams.team_detail.TeamDetailViewModel
import com.example.taskomanagement.ui.screen.main.teams.team_edit.TeamEditViewModel
import com.example.taskomanagement.ui.screen.main.teams.team_input.TeamInputViewModel
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
        MainRepositoryImpl(get(), get())
    }
    single {
        AuthDataStore(get())
    }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { TeamViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { TeamDetailViewModel(get()) }
    viewModel { ProjectDetailViewModel(get()) }
    viewModel { TaskViewModel(get()) }
    viewModel { TaskDetailViewModel(get()) }
    viewModel { TeamInputViewModel(get()) }
    viewModel { ProjectInputViewModel(get()) }
    viewModel { TaskInputViewModel(get()) }
    viewModel { MemberViewModel(get()) }
    viewModel { NavigationSharedViewModel() }
    viewModel { MessageViewModel(get()) }
    viewModel { MessageListViewModel(get()) }
    viewModel { MemberInputViewModel(get()) }
    viewModel { ProfileEditViewModel(get()) }
    viewModel { TeamEditViewModel(get()) }
    viewModel { ProjectEditViewModel(get()) }
    viewModel { TaskEditViewModel(get()) }
}