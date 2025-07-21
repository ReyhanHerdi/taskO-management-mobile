package com.example.taskomanagement.ui.screen.main.project.project_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.ui.cutom.CustomTasksList
import com.example.taskomanagement.utils.ShowCircularLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectDetail(
    projectId: Int,
    navController: NavController,
    viewModel: ProjectDetailViewModel = koinViewModel(),
) {
    val project by viewModel.project.collectAsState()
    val tasks by viewModel.task.collectAsState()
    val loadingResult = viewModel.dataResult.value

    LaunchedEffect(key1 = Unit) {
        viewModel.getTasks(projectId)
        viewModel.getProjectDetail(projectId)
    }
    val projectName = if (project != null) project?.nameProject else "Nama proyek"
    val projectDescription = if (project != null) project?.description else "Deskripsi proyek"
    val taskSize = if (tasks != null) tasks.size else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = "$projectName",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$taskSize Tugas",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(
            text = "Deskripsi",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = projectDescription ?: "Deskripsi",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Tenggat Waktu",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Tanggal tenggat",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "10 Juni 2003",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Tugas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate("TaskInputScreen/$projectId")
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "tambah tugas",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Buat",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            userScrollEnabled = true,
            modifier = Modifier
        ) {
            if (tasks != null) {
                tasks.forEach { task ->
                    item {
                        CustomTasksList(
                            tasks = task,
                            onItemClick = { selectedItem ->
                                navController.navigate("TaskDetailScreen/${selectedItem.idTask}")
                            }
                        )
                    }
                }
            }
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
//fun ProjectDetailPreview() {
//    ProjectDetail(0)
//}