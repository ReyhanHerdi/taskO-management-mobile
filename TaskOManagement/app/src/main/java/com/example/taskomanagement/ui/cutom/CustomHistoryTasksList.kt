package com.example.taskomanagement.ui.cutom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.data.response.TaskDataItem
import com.example.taskomanagement.utils.formatDate

@Composable
fun CustomHistoryTasksList(tasks: TaskDataItem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        border = CardDefaults.outlinedCardBorder(
            enabled = true
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "ongoing",
                tint = when (tasks.status) {
                    "ongoing" -> MaterialTheme.colorScheme.primary
                    "done" -> Color.Blue
                    "pending" -> Color.Red
                    else -> Color.White
                },
                modifier = Modifier
                    .size(width = 50.dp, height = 50.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = tasks.nameTask,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )
                Text(
                    text = formatDate(tasks.dueDate),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = tasks.dueTime,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "show detail",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CustomHistoryTasksListPreview() {
//    CustomHistoryTasksList(
//        Tasks(
//            nameTask = "Task 1",
//            project = "Project 1",
//            description = "Lorem ipsum dolor sit amet.",
//            due = "12 Juni 2025",
//            status = "ongoing"
//        )
//    )
//}
