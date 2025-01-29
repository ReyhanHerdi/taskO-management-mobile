package com.example.taskomanagement.ui.screen.main.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskomanagement.ui.theme.TaskOManagementTheme

@Composable
fun Home() {
    Text(text = "This is home")
}

@Preview(showBackground = true)
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    TaskOManagementTheme {
        Home()
    }
}