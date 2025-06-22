package com.example.taskomanagement.ui.screen.main.project

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.ui.cutom.CustomProjectsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun Projects(
    viewModel: ProjectViewModel = koinViewModel(),
) {
    val project by viewModel.project.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            text = "Daftar Proyek",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            viewModel.getTeam()
            project.forEach { project ->
                item {
                    CustomProjectsList(projects = project)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            onClick = {
                Log.d("FAB", "clicked")
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "tambah proyek"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjects() {
    Projects()
}