package com.example.taskomanagement.ui.screen.main.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.taskomanagement.aboutApplication
import com.example.taskomanagement.contactPerson
import com.example.taskomanagement.ui.cutom.CustomHistoryTasksList
import com.example.taskomanagement.utils.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Profile(
    navController: NavController,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val authStatus by viewModel.auth.collectAsState()
    val user by viewModel.user.collectAsState()
    val task by viewModel.task.collectAsState()
    val team by viewModel.team.collectAsState()
    val project by viewModel.project.collectAsState()

    val taskSize = if (task != null) task?.size else 0
    val teamSize = if (team != null) team.size else 0
    val projectSize = if (project != null) project.size else 0

    val context = LocalContext.current

    LaunchedEffect(key1 = authStatus) {
        if (!authStatus) {
            navController.popBackStack()
            navController.navigate(Screen.AuthenticationScreen.routes)
        }
        viewModel.getUser()
        viewModel.getTeam()
        viewModel.getTask()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                if (user.photoUrl == null) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Foto profil",
                        modifier = Modifier
                            .size(width = 70.dp, height = 70.dp)
                    )
                } else {
                    AsyncImage(
                        model = user.photoUrl,
                        contentDescription = "Foto profil",
                        modifier = Modifier
                            .size(width = 70.dp, height = 70.dp)
                            .clip(shape = RoundedCornerShape(50.dp))
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Text(
                        text = "${user.name}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "${user.email}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit profile",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(7.dp),
                modifier = Modifier.size(width = 100.dp, height = 80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "$taskSize",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Tugas",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(7.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(width = 100.dp, height = 80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "$projectSize",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Proyek",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(7.dp),
                modifier = Modifier
                    .size(width = 100.dp, height = 80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "$teamSize",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Tim",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.cardElevation(7.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Column(
                Modifier.padding(all = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Riwayat Tugas",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Lihat riwayat tugas",
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.HistoryTaskListScreen.routes)
                            }
                    )
                }
                LazyRow(
                    userScrollEnabled = true,
                    modifier = Modifier
                ) {
                    task?.forEach { exec ->
                        exec.task
                            .filter { it.status == "done" }
                            .sortedBy { it.updatedAt }
                            .forEach { tasks ->
                                item {
                                    CustomHistoryTasksList(tasks = tasks)
                                }
                        }
                    }
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.cardElevation(7.dp),
            modifier = Modifier
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(all = 16.dp)
            ) {
                Text(
                    text = "Tentang",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(aboutApplication))
                            context.startActivity(intent)
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Tentang aplikasi",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "Tentang Aplikasi",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Lihat tentang aplikasi"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(contactPerson))
                            context.startActivity(intent)
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Kontak Kami",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "Kontak Kami",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Lihat tentang aplikasi"
                    )
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.cardElevation(7.dp),
            modifier = Modifier
                .padding(top = 24.dp, bottom = 16.dp)
                .clickable(true) {
                    viewModel.logout()
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Keluar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Keluar",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfilePreview() {
//    Profile()
//}

