package com.example.taskomanagement.ui.screen.main.project.project_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomProjectsList
import com.example.taskomanagement.ui.cutom.CustomTasksList
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectDetail(
    projectId: Int,
    navController: NavController,
    viewModel: ProjectDetailViewModel = koinViewModel(),
) {
    viewModel.getProjectDetail(projectId)
    val project by viewModel.project.collectAsState()
    val projectName = if (project != null) project?.nameProject else "Nama proyek"
    val idTeam = if (project != null) project?.teamId else 0
    if (idTeam != null) {
        viewModel.getTeamDetail(idTeam)
    }
    val team by viewModel.team.collectAsState()
    val nameTeam = if (team != null) team?.nameTeam else "Nama tim"
    viewModel.getTasks(projectId)
    val tasks by viewModel.task.collectAsState()
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
                text = "$nameTeam",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = "|",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
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
            text = "Deskripsi",
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
        LazyColumn(
            userScrollEnabled = true,
            modifier = Modifier.padding(top = 8.dp)
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
}

//@Preview(showBackground = true)
//@Composable
//fun ProjectDetailPreview() {
//    ProjectDetail(0)
//}