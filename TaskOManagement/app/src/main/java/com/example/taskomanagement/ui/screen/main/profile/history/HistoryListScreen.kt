package com.example.taskomanagement.ui.screen.main.profile.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.ui.cutom.CustomHistoryTasksList
import com.example.taskomanagement.ui.screen.main.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun History(
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val task by viewModel.task.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getTask()
    }
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn {
            task?.forEach { exec ->
                exec.task
                    .filter { it.status == "done" }
                    .sortedBy { it.updatedAt }
                    .forEach { tasks ->
                    item {
                        CustomHistoryTasksList(tasks = tasks)
                    }
                }
            }
        }
    }
}