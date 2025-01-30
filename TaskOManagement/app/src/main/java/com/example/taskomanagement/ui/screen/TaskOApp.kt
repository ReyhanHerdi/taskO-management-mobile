package com.example.taskomanagement.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskomanagement.ui.navigation.BottomNavigationBar
import com.example.taskomanagement.ui.screen.main.home.Home
import com.example.taskomanagement.ui.screen.main.message.Message
import com.example.taskomanagement.ui.screen.main.profile.Profile
import com.example.taskomanagement.ui.screen.main.project.Projects
import com.example.taskomanagement.ui.screen.main.teams.Teams
import com.example.taskomanagement.utils.Screen

@Composable
fun TaskOApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, Modifier)
        },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.routes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.routes) { Home() }
            composable(Screen.ProjectsScreen.routes) { Projects() }
            composable(Screen.ProfileScreen.routes) { Profile() }
            composable(Screen.TeamsScreen.routes) { Teams() }
            composable(Screen.MessageScreen.routes) { Message() }
        }
    }
}