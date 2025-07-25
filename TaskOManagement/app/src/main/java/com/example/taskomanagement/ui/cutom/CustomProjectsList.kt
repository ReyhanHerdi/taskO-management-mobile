package com.example.taskomanagement.ui.cutom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.data.response.ProjectDataItem

@Composable
fun CustomProjectsList(
    projects: ProjectDataItem,
    onItemClick: (ProjectDataItem) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onItemClick(projects) }
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Row {
                Text(
                    text = projects.nameProject,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.weight(1f))
                when(projects.status) {
                    "pending" -> {
                        Icon(
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "notifikasi proyek",
                            tint = Color.Red
                        )
                    }
                    "ongoing" -> {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "notifikasi proyek",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    "done" -> {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "notifikasi proyek",
                            tint = Color.Blue
                        )
                    }
                }
            }
            Text(
                text = projects.description ?: "-",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )
            val projectsDone = if (projects.task != null) projects.task.filter { it.status == "done" }.size else 90
            val projectsTotal = if (projects.task != null) projects.task.size else 90
            val projectPercentage = projectsDone.toFloat() / projectsTotal.toFloat()
            LinearProgressIndicator(
                progress = { if (projectPercentage.isNaN()) 0f else projectPercentage },
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewProjectsList() {
//    CustomProjectsList(
//        Projects(
//        nameProject = "Proyek X",
//        description = "Lorem ipsum doloe sit amet",
//        due = "2 Juni 2025",
//        status = "ongoing",
//        nameTeam = "Team X"
//    )
//    )
//}