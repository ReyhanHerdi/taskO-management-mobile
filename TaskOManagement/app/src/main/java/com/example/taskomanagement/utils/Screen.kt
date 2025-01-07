package com.example.taskomanagement.utils

sealed class Screen(val routes: String) {
    data object LandingPageScreen: Screen("LandingPageScreen")
    data object LoginScreen: Screen("LoginScreen")
    data object RegisterScreen: Screen("RegisterScreen")
    data object RegisterSuccessScreen: Screen("RegisterSuccessScreen")
}