package com.example.taskomanagement.ui.screen.main.project.project_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectDetail(
    viewModel: ProjectDetailViewModel = koinViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nama Proyek",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "x Tugas",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}