package com.example.taskomanagement.ui.screen.main.member

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.taskomanagement.ui.cutom.CustomMemberList
import org.koin.androidx.compose.koinViewModel

@Composable
fun Member(
    teamId: Int,
    viewModel: MemberViewModel = koinViewModel(),
) {
    val member by viewModel.member.collectAsState()
    viewModel.getMember(teamId)
    Column {
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