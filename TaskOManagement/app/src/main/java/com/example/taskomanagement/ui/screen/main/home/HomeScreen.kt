package com.example.taskomanagement.ui.screen.main.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomTasksList
import com.example.taskomanagement.utils.Screen
import com.example.taskomanagement.utils.currentDate
import com.example.taskomanagement.utils.monthPicker
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
) {
    AuthCheck(navController = navController, viewModel = viewModel)
    val user by viewModel.user.collectAsState()
    val task by viewModel.task.collectAsState()
    viewModel.getUser()
    viewModel.getTask()
    var taskName: String? = null
    var taskDate: Date? = null
    var taskId: Int? = null
    if (!task.isNullOrEmpty()) {
        task?.forEach { exec ->
            exec.task
                .filter { it.status != "done" }
                .sortedBy { it.dueDate }
                .forEach {
                    taskDate = currentDate(it.dueDate, it.dueTime)
                    taskName = it.nameTask
                    taskId = it.idTask
                }
        }
    }
    Log.d("PARSE", taskDate.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            )
    ) {
        Text(
            text = "Selamat pagi,",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = user,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 20.dp,
                    bottom = 20.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 4.dp)
                ) {
                    Text(
                        text = "Tenggat waktu terdekat",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = taskName ?: "Tugas Terdekat",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                    )
                    Button(
                        onClick = {
                            navController.navigate("TaskDetailScreen/$taskId")
                        },
                        enabled = taskId != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        )
                    ) {
                        Text(
                            text = "Lihat",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Card (
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 117.dp, height = 117.dp)
                        .align(Alignment.CenterVertically)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = taskDate?.date?.toString() ?: "DD" ,
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp)
                            )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = monthPicker(taskDate?.month ?: 0),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 16.dp)
                        )
                    }
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                    )
            ) {
                Text(
                    text = "Target Hari Ini",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Periksa",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Daftar Tugas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Selengkapnya",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        navController.navigate(Screen.TaskScreen.routes)
                    }
            )
        }
        LazyColumn {
            task?.forEach { itemExec ->
                itemExec.task
                    .filter { it.status != "done" }
                    .sortedBy { it.dueDate }
                    .forEach { itemTask ->
                        if (itemTask != null) {
                            item {
                                CustomTasksList(
                                    tasks = itemTask,
                                    onItemClick = { selectedItem ->
                                        navController.navigate("TaskDetailScreen/${selectedItem.idTask}")
                                    }
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
//fun HomePreview(modifier: Modifier = Modifier) {
//    TaskOManagementTheme {
//        Home()
//    }
//}

@Composable
private fun AuthCheck(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(1) {
        scope.launch {
            if (!viewModel.getUserLogin()) {
                navController.popBackStack()
                navController.navigate(Screen.AuthenticationScreen.routes)
            }
        }
    }
}