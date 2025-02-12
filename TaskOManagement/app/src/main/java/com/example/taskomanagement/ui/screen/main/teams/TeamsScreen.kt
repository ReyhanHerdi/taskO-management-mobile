package com.example.taskomanagement.ui.screen.main.teams

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.ui.cutom.CustomTeamsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun Teams(
    viewModel: TeamViewModel = koinViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            text = "Daftar Tim",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )
        LazyColumn(
            userScrollEnabled = true,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            viewModel.getTeam()
            viewModel.team.value?.forEach { team ->
                item {
                    CustomTeamsList(team)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTeams(modifier: Modifier = Modifier) {
    Teams()
}