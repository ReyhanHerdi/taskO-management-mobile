package com.example.taskomanagement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskomanagement.ui.screen.TaskOApp
import com.example.taskomanagement.ui.screen.authentication.RegisterSuccess
import com.example.taskomanagement.ui.screen.authentication.login.Login
import com.example.taskomanagement.ui.screen.authentication.register.Register
import com.example.taskomanagement.ui.screen.landingPage.LandingPage
import com.example.taskomanagement.utils.Screen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LandingPageScreen.routes) {
        composable(route = Screen.LandingPageScreen.routes) { LandingPage(navController) }
        composable(route = Screen.RegisterScreen.routes) { Register(navController) }
        composable(route = Screen.LoginScreen.routes) { Login(navController) }
        composable(route = Screen.RegisterSuccessScreen.routes) { RegisterSuccess(navController) }
        composable(route = Screen.TaskOAppScreen.routes) { TaskOApp() }
    }
}