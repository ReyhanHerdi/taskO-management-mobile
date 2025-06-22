package com.example.taskomanagement.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskomanagement.data.response.TeamItem
import com.example.taskomanagement.ui.cutom.CustomBottomNavigationBar
import com.example.taskomanagement.ui.screen.authentication.RegisterSuccess
import com.example.taskomanagement.ui.screen.authentication.login.Login
import com.example.taskomanagement.ui.screen.authentication.register.Register
import com.example.taskomanagement.ui.screen.landingPage.LandingPage
import com.example.taskomanagement.ui.screen.main.home.Home
import com.example.taskomanagement.ui.screen.main.message.Message
import com.example.taskomanagement.ui.screen.main.profile.Profile
import com.example.taskomanagement.ui.screen.main.project.Projects
import com.example.taskomanagement.ui.screen.main.teams.Teams
import com.example.taskomanagement.ui.screen.main.teams.team_detail.TeamDetail
import com.example.taskomanagement.utils.Screen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomNavRoutes = listOf(
        Screen.HomeScreen.routes,
        Screen.ProjectsScreen.routes,
        Screen.MessageScreen.routes,
        Screen.TeamsScreen.routes,
        Screen.ProfileScreen.routes
    )
    Scaffold(
        bottomBar = { if(currentRoute in bottomNavRoutes) {
            CustomBottomNavigationBar(navController, Modifier)
        } },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.LandingPageScreen.routes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.LandingPageScreen.routes) { LandingPage(navController) }
            navigation(
                startDestination = Screen.LoginScreen.routes,
                route = Screen.AuthenticationScreen.routes
            ) {
                composable(route = Screen.RegisterScreen.routes) { Register(navController) }
                composable(route = Screen.LoginScreen.routes) { Login(navController) }
                composable(route = Screen.RegisterSuccessScreen.routes) { RegisterSuccess(navController) }
            }
            navigation(
                startDestination = Screen.HomeScreen.routes,
                route = Screen.MainScreen.routes
            ) {
                composable(Screen.HomeScreen.routes) { Home(navController) }
                composable(Screen.ProjectsScreen.routes) { Projects() }
                composable(Screen.ProfileScreen.routes) { Profile(navController) }
                composable(Screen.TeamsScreen.routes) { Teams(navController) }
                composable(Screen.MessageScreen.routes) { Message() }
            }
            composable(
                route = Screen.TeamDetailScreem.routes,
                arguments = listOf(navArgument("teamId") {
                    type = NavType.IntType
                })
            ) {
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (teamId != null) {
                    TeamDetail(teamId)
                }
            }
//        composable(route = Screen.TaskOAppScreen.routes) { TaskOApp() }
        }
    }

}