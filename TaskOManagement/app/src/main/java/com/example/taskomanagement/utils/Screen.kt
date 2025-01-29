package com.example.taskomanagement.utils

sealed class Screen(val routes: String) {
    data object LandingPageScreen: Screen("LandingPageScreen")
    data object LoginScreen: Screen("LoginScreen")
    data object RegisterScreen: Screen("RegisterScreen")
    data object RegisterSuccessScreen: Screen("RegisterSuccessScreen")
    data object TaskOAppScreen: Screen("TaskOApp")
    data object HomeScreen: Screen("HomeScreen")
    data object ProjectsScreen: Screen("ProjectsScreen")
    data object ProfileScreen: Screen("ProfileScreen")
}