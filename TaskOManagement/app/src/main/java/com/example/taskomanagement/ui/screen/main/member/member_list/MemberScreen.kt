package com.example.taskomanagement.ui.screen.main.member.member_list

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
import com.example.taskomanagement.ui.navigation.NavigationSharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Member(
    teamId: Int,
    navController: NavController,
    sharedViewModel: NavigationSharedViewModel,
    viewModel: MemberViewModel = koinViewModel(),
) {
    val member by viewModel.member.collectAsState()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMember(teamId)
        viewModel.getUser()
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
                            onItemClick = {
                                sharedViewModel.setUerName(it?.user?.get(0)?.name ?: "Kosong")
                                navController.navigate("ChatScreen/${it?.userId}")
                            },
                            userId = user.idUser ?: 0
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                navController.navigate("MemberInputScreen/$teamId")
            },
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