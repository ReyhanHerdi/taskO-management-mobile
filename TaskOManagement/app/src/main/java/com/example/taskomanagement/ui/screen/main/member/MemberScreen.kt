package com.example.taskomanagement.ui.screen.main.member

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.ui.cutom.CustomMemberList
import org.koin.androidx.compose.koinViewModel

@Composable
fun Member(
    teamId: Int,
    viewModel: MemberViewModel = koinViewModel(),
) {
    val member by viewModel.member.collectAsState()
    viewModel.getMember(teamId)
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn {
            if (member != null) {
                member.forEach {
                    item {
                        CustomMemberList(it)
                    }
                }
            }
        }
    }
}