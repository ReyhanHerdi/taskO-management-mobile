package com.example.taskomanagement.ui.screen.main.teams.team_list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomTeamsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun Teams(
    navController: NavController,
    viewModel: TeamViewModel = koinViewModel(),
) {
    val teams by viewModel.team.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn(
            userScrollEnabled = true,
        ) {
            viewModel.getTeam()
            if (teams != null) {
                teams.forEach { team ->
                    item {
                        CustomTeamsList(
                            teamDataItem = team,
                            onItemClick = { selectedItem ->
                                navController.navigate("TeamDetailScreen/${selectedItem.teamId}")
                            }
                        )
                    }
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewTeams() {
//    Teams()
//}