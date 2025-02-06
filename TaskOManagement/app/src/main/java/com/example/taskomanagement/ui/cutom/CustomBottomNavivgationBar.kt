package com.example.taskomanagement.ui.cutom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskomanagement.utils.BottomNavItem
import com.example.taskomanagement.utils.Screen


@Composable
fun CustomBottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier.shadow(12.dp)
    ) {
        val items = listOf(
            BottomNavItem(
                route = Screen.HomeScreen.routes,
                iconSelected = Icons.Filled.Home,
                iconUnSelected = Icons.Outlined.Home,
                label = "Home"
            ),
            BottomNavItem(
                route = Screen.ProjectsScreen.routes,
                iconSelected = Icons.Filled.DateRange,
                iconUnSelected = Icons.Outlined.DateRange,
                label = "Projects"
            ),
            BottomNavItem(
                route = Screen.MessageScreen.routes,
                iconSelected = Icons.Filled.Email,
                iconUnSelected = Icons.Outlined.Email,
                label = "Message"
            ),
            BottomNavItem(
                route = Screen.TeamsScreen.routes,
                iconSelected = Icons.Filled.AccountBox,
                iconUnSelected = Icons.Outlined.AccountBox,
                label = "Teams"
            ),
            BottomNavItem(
                route = Screen.ProfileScreen.routes,
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
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledTextColor = MaterialTheme.colorScheme.primary,
                    disabledIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Screen.HomeScreen.routes) {
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