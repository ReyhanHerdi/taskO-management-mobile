package com.example.taskomanagement.utils

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val iconSelected: ImageVector,
    val iconUnSelected: ImageVector,
    val label: String
)