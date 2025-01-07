package com.example.taskomanagement.utils

sealed class Screen(val routes: String) {
    data object LoginScreen: Screen("LoginScreen")
    data object LandingPageScreen: Screen("LandingPageScreen")
}