package com.example.taskomanagement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskomanagement.ui.screen.authentication.login.Login
import com.example.taskomanagement.ui.screen.landingPage.LandingPage
import com.example.taskomanagement.utils.Screen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LandingPageScreen.routes) {
        composable(route = Screen.LandingPageScreen.routes) { LandingPage(navController) }
        composable(route = Screen.LoginScreen.routes) { Login() }
    }
}