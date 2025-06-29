package com.example.taskomanagement.ui.screen.main.project.project_input

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomTeamsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectInputChooseTeam(
    navController: NavController,
    viewModel: ProjectInputViewModel = koinViewModel(),
) {
    val team by viewModel.team.collectAsState()
    viewModel.getTeam()
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn {
            if (team != null) {
                item {
                    team.forEach { data ->
                        CustomTeamsList(teamDataItem = data) { selectedItem ->
                            navController.navigate("ProjectInputScreen/${selectedItem.teamId}")
                        }
                    }
                }
            }
        }
    }
}