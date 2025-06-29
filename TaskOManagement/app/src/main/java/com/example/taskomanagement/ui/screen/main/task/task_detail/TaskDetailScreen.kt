package com.example.taskomanagement.ui.screen.main.task.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskDetail(
    taskId: Int,
    viewModel: TaskDetailViewModel = koinViewModel(),
) {
    val task by viewModel.task.collectAsState()
    val project by viewModel.project.collectAsState()

    viewModel.getTaskById(taskId)
    viewModel.getProjectById(task?.idTask ?: 0)

    val nameTask = task?.nameTask ?: "Name Tugas"
    val nameProject = project?.nameProject ?: "Nama Proyek"
    val descriptionTask = task?.description ?: "Dekripsi tugas"
    val dueDateTask = task?.dueDate ?: "10 Juni 2003"
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
        Text(
            text = nameProject,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
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
        Text(
            text = "Tenggat Waktu",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = dueDateTask,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Tandai tugas selesai"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailPreview() {
    TaskDetail(
        taskId =  0
    )
}