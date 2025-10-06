package com.example.taskomanagement.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.example.taskomanagement.ui.screen.main.member.member_input.MemberInput
import com.example.taskomanagement.ui.screen.main.member.member_list.Member
import com.example.taskomanagement.ui.screen.main.message.message_chat.Chat
import com.example.taskomanagement.ui.screen.main.message.message_list.Message
import com.example.taskomanagement.ui.screen.main.profile.Profile
import com.example.taskomanagement.ui.screen.main.profile.history.History
import com.example.taskomanagement.ui.screen.main.profile.profile_edit.ProfileEdit
import com.example.taskomanagement.ui.screen.main.project.project_detail.ProjectDetail
import com.example.taskomanagement.ui.screen.main.project.project_edit.ProjectEdit
import com.example.taskomanagement.ui.screen.main.project.project_input.ProjectInput
import com.example.taskomanagement.ui.screen.main.project.project_input.ProjectInputChooseTeam
import com.example.taskomanagement.ui.screen.main.project.project_list.Projects
import com.example.taskomanagement.ui.screen.main.task.task_detail.TaskDetail
import com.example.taskomanagement.ui.screen.main.task.task_edit.TaskEdit
import com.example.taskomanagement.ui.screen.main.task.task_input.TaskInput
import com.example.taskomanagement.ui.screen.main.task.task_list.Task
import com.example.taskomanagement.ui.screen.main.teams.team_detail.TeamDetail
import com.example.taskomanagement.ui.screen.main.teams.team_edit.TeamEdit
import com.example.taskomanagement.ui.screen.main.teams.team_input.TeamInput
import com.example.taskomanagement.ui.screen.main.teams.team_list.Teams
import com.example.taskomanagement.utils.Screen
import org.koin.androidx.compose.koinViewModel

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
    val sharedViewModel: NavigationSharedViewModel = koinViewModel()
    Scaffold(
        topBar = {
            if (currentRoute !in nonTopAppBarRoutes) {
                when(currentRoute) {
                    Screen.HomeScreen.routes -> CustomTopAppBar(showBackIcon = false, endIcon = Icons.Outlined.Notifications)
                    Screen.ProjectsScreen.routes -> CustomTopAppBar(title = "Daftar Proyek", showBackIcon = false)
                    Screen.MessageScreen.routes -> CustomTopAppBar(title = "Daftar Pesan", showBackIcon = false)
                    Screen.TeamsScreen.routes -> CustomTopAppBar(title = "Daftar Tim", showBackIcon = false)
                    Screen.ProfileScreen.routes -> CustomTopAppBar(title = "Akun Saya", showBackIcon = false, endIcon = Icons.Filled.Settings)
                    Screen.TeamDetailScreen.routes -> CustomTopAppBar(
                        showBackIcon = true,
                        endIcon2 = ImageVector.vectorResource(id = R.drawable.baseline_groups_24),
                        endIcon = Icons.Filled.Settings,
                        onClick1 = {
                            sharedViewModel.teamId?.let { id ->
                                navController.navigate("TeamEditScreen/$id")
                            } ?: Log.d("CLICK", "no respond")
                        },
                        onClick2 = {
                            sharedViewModel.teamId?.let { id ->
                                navController.navigate("MembersScreen/$id")
                            } ?: Log.d("CLICK", "no respond")
                        },
                        navController = navController
                    )
                    Screen.ProjectDetailScreen.routes -> CustomTopAppBar(
                        showBackIcon = true,
                        endIcon = Icons.Default.Settings,
                        onClick1 = {
                            sharedViewModel.projectId?.let { id ->
                                navController.navigate("ProjectEditScreen/$id")
                            } ?: Log.d("CLICK", "no respond")
                        },
                        navController = navController
                    )
                    Screen.TaskScreen.routes -> CustomTopAppBar(title = "Daftar Tugas Saya", showBackIcon = true, navController = navController)
                    Screen.TaskDetailScreen.routes -> CustomTopAppBar(
                        endIcon = Icons.Filled.Settings,
                        showBackIcon = true,
                        onClick1 = {
                            sharedViewModel.taskId?.let { id ->
                                navController.navigate("TaskEditScreen/$id")
                            } ?: Log.d("CLICK", "no respond")
                        },
                        navController = navController
                    )
                    Screen.TeamInputScreen.routes -> CustomTopAppBar(title = "Buat Tim", showBackIcon = true, navController = navController)
                    Screen.ProjectChooseTeamScreen.routes -> CustomTopAppBar(title = "Pilih Tim", showBackIcon = true, navController = navController)
                    Screen.ProjectInputScreen.routes -> CustomTopAppBar(title = "Buat Proyek", showBackIcon = true, navController = navController)
                    Screen.TaskInputScreen.routes -> CustomTopAppBar(title = "Buat Tugas", showBackIcon = true, navController = navController)
                    Screen.MembersScreen.routes -> CustomTopAppBar(title = "Daftar Anggota", showBackIcon = true, navController = navController)
                    Screen.MessageScreen.routes -> CustomTopAppBar(showBackIcon = false, title = "Daftar Pesan", navController = navController)
                    Screen.ChatScreen.routes -> CustomTopAppBar(
                        title = sharedViewModel.userName,
                        showBackIcon = true
                    )
                    Screen.MemberInputScreen.routes -> CustomTopAppBar(showBackIcon = true, title = "Tambah Anggota", navController = navController)
                    Screen.HistoryTaskListScreen.routes -> CustomTopAppBar(showBackIcon = true, title = "Riwayat Tugas", navController = navController)
                    Screen.ProfileEditScreen.routes -> CustomTopAppBar(showBackIcon = true, title = "Edit Profil", navController = navController)
                    Screen.TeamEditScreen.routes -> CustomTopAppBar(showBackIcon = true, title = "Edit Tim", navController = navController)
                    Screen.ProjectEditScreen.routes -> CustomTopAppBar(showBackIcon = true, navController = navController)
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
            modifier = Modifier
                .padding(innerPadding)
                .padding(WindowInsets.navigationBars.asPaddingValues())
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
                composable(Screen.MessageScreen.routes) { Message(navController, sharedViewModel) }
            }
            composable(Screen.TaskScreen.routes) { Task(navController) }
            composable(
                route = Screen.TeamDetailScreen.routes,
                arguments = listOf(navArgument("teamId") {
                    type = NavType.IntType
                })
            ) {
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (teamId != null) {
                    TeamDetail(teamId, navController, sharedViewModel)
                }
            }
            composable(
                route = Screen.MembersScreen.routes,
                arguments = listOf(navArgument("teamId") {
                    type = NavType.IntType
                })
            ) {
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (teamId != null) {
                    Member(teamId, navController, sharedViewModel)
                }
            }
            composable(
                route = Screen.TeamEditScreen.routes,
                arguments = listOf(navArgument("teamId") {
                    type = NavType.IntType
                })
            ) {
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (teamId != null) {
                    TeamEdit(teamId)
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
                    ProjectDetail(projectId, navController, sharedViewModel)
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
                    TaskDetail(taskId, navController, sharedViewModel)
                }
            }
            composable(
                route = Screen.TaskEditScreen.routes,
                arguments = listOf(navArgument("taskId") {
                    type = NavType.IntType
                })
            ) {
                val taskId = navBackStackEntry?.arguments?.getInt("taskId")
                if (taskId != null) {
                    TaskEdit(taskId)
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
                    ProjectInput(teamId = teamId, navController)
                }
            }
            composable(
                route = Screen.ProjectEditScreen.routes,
                arguments = listOf(navArgument("projectId") {
                    type = NavType.IntType
                })
            ) {
                val projectId = navBackStackEntry?.arguments?.getInt("projectId")
                if (projectId != null) {
                    ProjectEdit(projectId = projectId)
                }
            }
            composable(
                route = Screen.TaskInputScreen.routes,
                arguments = listOf(navArgument("projectId") {
                    type = NavType.IntType
                })
            ) {
                val projectId = navBackStackEntry?.arguments?.getInt("projectId")
                if (projectId != null){
                    TaskInput(projectId = projectId, navController = navController)
                }
            }
            composable(
                route = Screen.ChatScreen.routes,
                arguments = listOf(
                    navArgument("teamId") {
                        type = NavType.IntType
                    }, navArgument("memberId") {
                        type = NavType.IntType
                    }
                )
            ) {
                val memberId = navBackStackEntry?.arguments?.getInt("memberId")
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (memberId != null && teamId != null) {
                    Chat(teamId = teamId, memberId = memberId)
                }
            }
            composable(
                route = Screen.MemberInputScreen.routes,
                arguments = listOf(navArgument("teamId") {
                    type = NavType.IntType
                })
            ) {
                val teamId = navBackStackEntry?.arguments?.getInt("teamId")
                if (teamId != null) {
                    MemberInput(teamId = teamId, navController = navController)
                }
            }
            composable(route = Screen.HistoryTaskListScreen.routes) { History() }
            composable(route = Screen.ProfileEditScreen.routes) { ProfileEdit() }
//        composable(route = Screen.TaskOAppScreen.routes) { TaskOApp() }
        }
    }

}