package com.example.taskomanagement.ui.cutom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String? = null,
    showBackIcon: Boolean,
    endIcon: ImageVector? = null,
    endIcon2: ImageVector? = null,
    onClick1: (() -> Unit)? = null,
    onClick2: (() -> Unit)? = null,
    navController: NavController? = null
) {
    TopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            if (showBackIcon) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            navController?.popBackStack()
                        }
                )
            }
        },
        actions = {
            if (endIcon2 != null) {
                Icon(
                    imageVector = endIcon2,
                    contentDescription = "Fitur lainnya",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                        .clickable(onClick = onClick2!!)
                )
            }
            if (endIcon != null) {
                if (onClick1 != null) {
                    Icon(
                        imageVector = endIcon,
                        contentDescription = "Notifikasi",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(onClick = onClick1)
                    )
                } else {
                    Icon(
                        imageVector = endIcon,
                        contentDescription = "Notifikasi",
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.tertiary,
                actionIconContentColor = MaterialTheme.colorScheme.tertiary,
                navigationIconContentColor = MaterialTheme.colorScheme.tertiary
            )
    )
}

//@Preview(showBackground = true)
//@Composable
//fun CustomTopAppBarPreview() {
//    CustomTopAppBar(
//        title = "Preview Screen",
//        subtitle = "Ini konteks",,
//        startIcon = Icons.Filled.PlayArrow,
//        endIcon = Icons.Filled.PlayArrow,
//    )
//}