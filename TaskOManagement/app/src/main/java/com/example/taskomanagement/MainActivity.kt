package com.example.taskomanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.taskomanagement.ui.navigation.Navigation
import com.example.taskomanagement.ui.theme.TaskOManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskOManagementTheme {
                Navigation()
            }
        }
    }
}
