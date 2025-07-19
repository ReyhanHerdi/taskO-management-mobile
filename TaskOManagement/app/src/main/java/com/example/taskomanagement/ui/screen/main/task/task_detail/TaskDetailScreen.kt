package com.example.taskomanagement.ui.screen.main.task.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.utils.ShowCircularLoading
import com.example.taskomanagement.utils.formatDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskDetail(
    taskId: Int,
    navController: NavController,
    viewModel: TaskDetailViewModel = koinViewModel(),
) {
    val task by viewModel.task.collectAsState()
    val project by viewModel.project.collectAsState()
    val executor by viewModel.executor.collectAsState()
    val loadingResult = viewModel.dataResult.value

    LaunchedEffect(key1 = Unit) {
        viewModel.getTaskById(taskId)
        viewModel.getProjectById(task?.idTask ?: 0)
        viewModel.getExecutorByTaskId(taskId)
    }

    val nameTask = task?.nameTask ?: "Name Tugas"
    val nameProject = project?.nameProject ?: "Nama Proyek"
    val descriptionTask = task?.description ?: "Dekripsi tugas"
    val dueDateTask = task?.dueDate ?: "2003-06-10"
    val dueTimeTask = task?.dueTime ?: "00.00.00"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = nameTask,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
//        Text(
//            text = nameProject,
//            style = MaterialTheme.typography.titleSmall,
//            fontWeight = FontWeight.Normal,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//        )
        Text(
            text = "Tenggat Waktu",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "${formatDate(dueDateTask)} | $dueTimeTask",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Penanggung Jawab",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 20.dp)
        )
        if (executor.isNullOrEmpty()) {
            Text(
                text = "Belum ada penanggung jawab",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        LazyRow {
            executor?.forEach { task ->
                task.user.forEach { taskExecutor ->
                    item {
                        Row(
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            if (taskExecutor.photoUrl == null) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "Foto profil penanggung jawab",
                                    modifier = Modifier
                                        .padding(end = 4.dp)
                                        .size(width = 20.dp, height = 20.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            } else {
                                AsyncImage(
                                    model = taskExecutor.photoUrl,
                                    contentDescription = "Foro profil penanggung jawab",
                                    modifier = Modifier
                                        .padding(end = 4.dp)
                                        .size(width = 20.dp, height = 20.dp)
                                        .align(Alignment.CenterVertically)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                            Text(
                                text = taskExecutor.name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
        Text(
            text = "Deskripsi",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = descriptionTask,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            onClick = {
                if (task?.status != "done") {
                    var taskStatus = ""
                    when (task?.status) {
                        "pending" -> taskStatus = "ongoing"
                        "ongoing" -> taskStatus = "done"
                        else -> { /* DO NOTHING */ }
                    }
                    viewModel.setTaskDone(taskId, taskStatus)
                    navController.popBackStack()
                }
            },
            containerColor = if (task?.status == "done") MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = if (task?.status == "pending") Icons.Default.PlayArrow else Icons.Default.Done,
                contentDescription = "Tandai tugas selesai"
            )
        }
    }
    when (loadingResult) {
        is Result.Loading -> ShowCircularLoading(isLoading = true)
        is Result.Success -> ShowCircularLoading(isLoading = false)
        is Result.Error -> ShowCircularLoading(isLoading = false)
        null -> { /* DO NOTHING */ }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TaskDetailPreview() {
//    TaskDetail(
//        taskId =  0
//    )
//}