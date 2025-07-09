package com.example.taskomanagement.ui.screen.main.project.project_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomProjectsList
import com.example.taskomanagement.utils.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Projects(
    navController: NavController,
    viewModel: ProjectViewModel = koinViewModel(),
) {
    val project by viewModel.project.collectAsState()
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            LazyColumn {
                viewModel.getTeam()
                project.forEach { project ->
                    item {
                        CustomProjectsList(
                            projects = project,
                            onItemClick = { selectedItem ->
                                navController.navigate("ProjectDetailScreen/${selectedItem.idProject}")
                            }
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.ProjectChooseTeamScreen.routes)
            },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
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
//fun PreviewProjects() {
//    Projects()
//}