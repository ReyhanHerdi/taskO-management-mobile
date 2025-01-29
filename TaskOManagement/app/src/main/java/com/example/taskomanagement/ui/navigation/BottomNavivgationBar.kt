package com.example.taskomanagement.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskomanagement.utils.BottomNavItem


@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    NavigationBar {
        val items = listOf(
            BottomNavItem(
                route = "HomeScreen",
                iconSelected = Icons.Filled.Home,
                iconUnSelected = Icons.Outlined.Home,
                label = "Home"
            ),
            BottomNavItem(
                route = "ProjectsScreen",
                iconSelected = Icons.Filled.DateRange,
                iconUnSelected = Icons.Filled.DateRange,
                label = "Projects"
            ),
            BottomNavItem(
                route = "MessageScreen",
                iconSelected = Icons.Filled.Email,
                iconUnSelected = Icons.Outlined.Email,
                label = "Message"
            ),
            BottomNavItem(
                route = "ProfileScreen",
                iconSelected = Icons.Filled.Person,
                iconUnSelected = Icons.Outlined.Person,
                label = "Profile"
            )
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.map { item  ->
            NavigationBarItem(
                icon = { Icon(imageVector = if (currentRoute == item.route) item.iconSelected else item.iconUnSelected,
                    contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}