package com.example.taskomanagement.ui.screen.main.member

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.R
import com.example.taskomanagement.ui.cutom.CustomMemberList
import com.example.taskomanagement.utils.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Member(
    teamId: Int,
    navController: NavController,
    viewModel: MemberViewModel = koinViewModel(),
) {
    val member by viewModel.member.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getMember(teamId)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn {
            if (member != null) {
                member.forEach {
                    item {
                        CustomMemberList(
                            member = it,
                            onItemClick = { navController.navigate(Screen.ChatScreen.routes) }
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_person_add_alt_1_24),
                contentDescription = "tambah anggota",
                modifier = Modifier
            )
        }
    }
}