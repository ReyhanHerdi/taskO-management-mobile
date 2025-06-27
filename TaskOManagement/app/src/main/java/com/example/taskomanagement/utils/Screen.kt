package com.example.taskomanagement.utils

sealed class Screen(val routes: String) {
    data object LandingPageScreen: Screen("LandingPageScreen")
    data object LoginScreen: Screen("LoginScreen")
    data object RegisterScreen: Screen("RegisterScreen")
    data object RegisterSuccessScreen: Screen("RegisterSuccessScreen")
    data object HomeScreen: Screen("HomeScreen")
    data object ProjectsScreen: Screen("ProjectsScreen")
    data object MessageScreen: Screen("MessageScreen")
    data object TeamsScreen: Screen("TeamsScreen")
    data object ProfileScreen: Screen("ProfileScreen")
    data object MainScreen: Screen("MainScreen")
    data object AuthenticationScreen: Screen("AuthenticationScreen")
    data object TeamDetailScreem: Screen("TeamDetailScreen/{teamId}")
    data object ProjectDetailScreen: Screen("ProjectDetailScreen/{projectId}")
}