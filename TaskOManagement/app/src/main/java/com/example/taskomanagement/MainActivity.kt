package com.example.taskomanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.taskomanagement.ui.navigation.Navigation
import com.example.taskomanagement.ui.theme.TaskOManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskOManagementTheme {
                Surface (
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Navigation()
                }
            }
        }
    }
}
