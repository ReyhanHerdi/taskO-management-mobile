package com.example.taskomanagement.ui.screen.main.teams.team_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomProjectsList
import com.example.taskomanagement.ui.cutom.CustomTeamsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamDetail(
    teamId: Int,
    navController: NavController,
    viewModel: TeamDetailViewModel = koinViewModel(),
) {
    viewModel.getTeam(teamId)
    viewModel.getProjects(teamId)
    val team by viewModel.team.collectAsState()
    val projects by viewModel.project.collectAsState()
    val projectSize = if (projects != null) projects.size else 0

    val nameTeam = if (team != null) team?.nameTeam else "Nama Tim"
    val descriptionTeam = if (team != null) team?.description else "-"
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "$nameTeam",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "$projectSize Proyek",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Deskripsi",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "$descriptionTeam",
            style = MaterialTheme.typography.bodyMedium
        )
        Row {
            Text(
                text = "Proyek",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "tambah pryoek",
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
            if (projects != null) {
                projects.forEach { project ->
                    item {
                        project?.let {
                            CustomProjectsList(
                                projects = it,
                                onItemClick = { selectedItem ->
                                    navController.navigate("ProjectDetailScreen/${selectedItem.idProject}")
                                },
                            )
                        }
                    }
                }
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun TeamDetailPreview(
//) {
//    TeamDetail(1, koinViewModel())
//}