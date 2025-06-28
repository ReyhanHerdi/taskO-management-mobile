package com.example.taskomanagement.ui.screen.main.task.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
    viewModel.getTaskById(taskId)
    val task by viewModel.task.collectAsState()
    viewModel.getProjectById(task?.idTask ?: 0)
    val project by viewModel.project.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = task?.nameTask ?: "Nama Tugas",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = project?.nameProject ?: "Nama Proyek",
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
            text = task?.description ?: "Deskripsi tugas",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Tenggat Waktu",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = task?.due ?: "10 Juni 2003",
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