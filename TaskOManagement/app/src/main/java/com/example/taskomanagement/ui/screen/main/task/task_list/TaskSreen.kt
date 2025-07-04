package com.example.taskomanagement.ui.screen.main.task.task_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomTasksList
import org.koin.androidx.compose.koinViewModel

@Composable
fun Task(
    navController: NavController,
    viewModel: TaskViewModel = koinViewModel(),
) {
    viewModel.getTaskByExecutor()
    val task by viewModel.task.collectAsState()
    Column {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            if (task != null) {
                task.forEach { tasks ->
                    item {
                        CustomTasksList(
                            tasks = tasks,
                            onItemClick = { selectedItem ->
                                navController.navigate("TaskDetailScreen/${selectedItem.idTask}")
                            }
                        )
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