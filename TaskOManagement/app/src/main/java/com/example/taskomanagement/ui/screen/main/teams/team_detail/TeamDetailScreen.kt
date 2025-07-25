package com.example.taskomanagement.ui.screen.main.teams.team_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.taskomanagement.ui.cutom.CustomProjectsList
import com.example.taskomanagement.ui.navigation.NavigationSharedViewModel
import com.example.taskomanagement.utils.ShowCircularLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamDetail(
    teamId: Int,
    navController: NavController,
    sharedViewModel: NavigationSharedViewModel,
    viewModel: TeamDetailViewModel = koinViewModel(),
) {
    val team by viewModel.team.collectAsState()
    val projects by viewModel.project.collectAsState()
    val loadingResult = viewModel.dataResult.value

    LaunchedEffect(key1 = Unit) {
        viewModel.getTeam(teamId)
        viewModel.getProjects(teamId)
    }

    val nameTeam = team?.nameTeam ?: "Nama Tim"
    val descriptionTeam = team?.description ?: "-"
    val projectSize = projects?.size ?: 0

    sharedViewModel.setTeamId(teamId)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Column {
               Text(
                   text = nameTeam,
                   style = MaterialTheme.typography.titleLarge,
                   fontWeight = FontWeight.SemiBold,
               )
               Text(
                   text = "$projectSize Proyek",
                   style = MaterialTheme.typography.titleSmall,
                   fontWeight = FontWeight.Normal,
                   modifier = Modifier.align(Alignment.CenterHorizontally)
               )
           }
        }
        Text(
            text = "Deskripsi",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = descriptionTeam,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Proyek",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate("ProjectInputScreen/$teamId")
            }) {
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
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            userScrollEnabled = true,
        ) {
            projects?.forEach { project ->
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
    when (loadingResult) {
        is Result.Loading -> ShowCircularLoading(isLoading = true)
        is Result.Success -> ShowCircularLoading(isLoading = false)
        is Result.Error -> ShowCircularLoading(isLoading = false)
        null -> { /* DO NOTHING */ }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TeamDetailPreview(
//) {
//    TeamDetail(1, koinViewModel())
//}