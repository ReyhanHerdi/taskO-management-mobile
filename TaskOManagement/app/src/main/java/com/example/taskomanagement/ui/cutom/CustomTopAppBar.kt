package com.example.taskomanagement.ui.cutom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String? = null,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null
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
            if (startIcon != null) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        },
        actions = {
            if (endIcon != null) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = "Notifikasi",
                    modifier = Modifier.padding(end = 16.dp)
                )
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