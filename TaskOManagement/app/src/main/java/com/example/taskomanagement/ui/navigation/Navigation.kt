package com.example.taskomanagement.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskomanagement.R
import com.example.taskomanagement.ui.cutom.CustomBottomNavigationBar
import com.example.taskomanagement.ui.cutom.CustomTopAppBar
import com.example.taskomanagement.ui.screen.authentication.RegisterSuccess
import com.example.taskomanagement.ui.screen.authentication.login.Login
import com.example.taskomanagement.ui.screen.authentication.register.Register
import com.example.taskomanagement.ui.screen.landingPage.LandingPage
import com.example.taskomanagement.ui.screen.main.home.Home
import com.example.taskomanagement.ui.screen.main.message.Message
import com.example.taskomanagement.ui.screen.main.profile.Profile
import com.example.taskomanagement.ui.screen.main.project.project_detail.ProjectDetail
import com.example.taskomanagement.ui.screen.main.project.project_input.ProjectInput
import com.example.taskomanagement.ui.screen.main.project.project_input.ProjectInputChooseTeam
import com.example.taskomanagement.ui.screen.main.project.project_list.Projects
import com.example.taskomanagement.ui.screen.main.task.task_detail.TaskDetail
import com.example.taskomanagement.ui.screen.main.task.task_list.Task
import com.example.taskomanagement.ui.screen.main.teams.team_detail.TeamDetail
import com.example.taskomanagement.ui.screen.main.teams.team_input.TeamInput
import com.example.taskomanagement.ui.screen.main.teams.team_list.Teams
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
    val nonTopAppBarRoutes = listOf(
        Screen.LandingPageScreen.routes,
        Screen.AuthenticationScreen.routes
    )

    Scaffold(
        topBar = {
            if (currentRoute !in nonTopAppBarRoutes) {
                when(currentRoute) {
                    Screen.HomeScreen.routes -> CustomTopAppBar(endIcon = Icons.Outlined.Notifications)
                    Screen.ProjectsScreen.routes -> CustomTopAppBar(title = "Daftar Proyek")
                    Screen.MessageScreen.routes -> CustomTopAppBar(title = "Daftar Pesan")
                    Screen.TeamsScreen.routes -> CustomTopAppBar(title = "Daftar Tim")
                    Screen.ProfileScreen.routes -> CustomTopAppBar(title = "Akun Saya", endIcon = Icons.Filled.Settings)
                    Screen.TeamDetailScreem.routes -> CustomTopAppBar(
                        startIcon = Icons.Filled.ArrowBack,
                        endIcon2 = ImageVector.vectorResource(id = R.drawable.baseline_groups_24),
                        endIcon = Icons.Filled.Settings
                    )
                    Screen.ProjectDetailScreen.routes -> CustomTopAppBar(startIcon = Icons.Filled.ArrowBack, endIcon = Icons.Filled.Settings)
                    Screen.TaskScreen.routes -> CustomTopAppBar(title = "Daftar Tugas Saya", startIcon = Icons.Filled.ArrowBack)
                    Screen.TaskDetailScreen.routes -> CustomTopAppBar(startIcon = Icons.Filled.ArrowBack, endIcon = Icons.Filled.Settings)
                    Screen.TeamInputScreen.routes -> CustomTopAppBar(title = "Buat Tim", startIcon = Icons.Filled.ArrowBack)
                    Screen.ProjectChooseTeamScreen.routes -> CustomTopAppBar(title = "Pilih Tim", startIcon = Icons.Filled.ArrowBack)
                    Screen.ProjectInputScreen.routes -> CustomTopAppBar(title = "Buat Proyek", startIcon = Icons.Filled.ArrowBack)
                }
            }
        },
        bottomBar = {
            if(currentRoute in bottomNavRoutes) {
                CustomBottomNavigationBar(
                    navController = navController,
                    modifier = Modifier,

                )
            }
        },
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
                composable(Screen.ProjectsScreen.routes) { Projects(navController) }
                composable(Screen.ProfileScreen.routes) { Profile(navController) }
                composable(Screen.TeamsScreen.routes) { Teams(navController) }
                composable(Screen.MessageScreen.routes) { Message() }
            }
            composable(Screen.TaskScreen.routes) { Task(navController) }
            composable(
                route = Screen.TeamDetailScreem.routes,
                arguments = listOf(navArgument("teamId") {
                    type = NavType.IntType
                })
            ) {
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (teamId != null) {
                    TeamDetail(teamId, navController)
                }
            }
            composable(
                route = Screen.ProjectDetailScreen.routes,
                arguments = listOf(navArgument("projectId") {
                    type = NavType.IntType
                })
            ) {
                val projectId = navBackStackEntry?.arguments?.getInt("projectId")
                if (projectId != null) {
                    ProjectDetail(projectId, navController)
                }
            }
            composable(
                route = Screen.TaskDetailScreen.routes,
                arguments = listOf(navArgument("taskId") {
                    type = NavType.IntType
                })
            ) {
                val taskId = navBackStackEntry?.arguments?.getInt("taskId")
                if (taskId != null) {
                    TaskDetail(taskId)
                }
            }
            composable(route = Screen.TeamInputScreen.routes) { TeamInput(navController) }
            composable(route = Screen.ProjectChooseTeamScreen.routes) { ProjectInputChooseTeam(navController) }
            composable(
                route = Screen.ProjectInputScreen.routes,
                arguments = listOf(navArgument("teamId") {
                    type = NavType.IntType
                })
            ) {
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (teamId != null) {
                    ProjectInput(teamId = teamId)
                }
            }
//        composable(route = Screen.TaskOAppScreen.routes) { TaskOApp() }
        }
    }

}