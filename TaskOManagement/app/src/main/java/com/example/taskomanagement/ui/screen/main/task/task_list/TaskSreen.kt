package com.example.taskomanagement.ui.screen.main.task.task_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.ui.cutom.CustomTasksList
import com.example.taskomanagement.utils.ShowLinearLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun Task(
    navController: NavController,
    viewModel: TaskViewModel = koinViewModel(),
) {
    val task by viewModel.task.collectAsState()
    val loadingResult = viewModel.dataResult.value

    LaunchedEffect(key1 = Unit) {
        viewModel.getTaskByExecutor()
    }
    Column {
        when (loadingResult) {
            is Result.Loading -> ShowLinearLoading(isLoading = true)
            is Result.Success -> ShowLinearLoading(isLoading = false)
            is Result.Error -> ShowLinearLoading(isLoading = false)
            null -> { /* DO NOTHING */ }
        }
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            if (task != null) {
                task.forEach { exec ->
                    exec.task
                        .filter { it.status != "done" }
                        .sortedBy { it.createdAt }
                        .forEach { tasks ->
                        item {
                            CustomTasksList(tasks = tasks) {
                                navController.navigate("TaskDetailScreen/${tasks.idTask}")
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TaskPreview() {
//    Task()
//}