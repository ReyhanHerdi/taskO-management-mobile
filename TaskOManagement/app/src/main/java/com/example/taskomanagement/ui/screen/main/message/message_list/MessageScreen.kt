package com.example.taskomanagement.ui.screen.main.message.message_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.ui.cutom.CustomMessageList
import com.example.taskomanagement.ui.navigation.NavigationSharedViewModel
import com.example.taskomanagement.utils.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Message(
    navController: NavController,
    sharedViewModel: NavigationSharedViewModel,
    viewModel: MessageListViewModel = koinViewModel(),
) {
    val messageList by viewModel.messages.collectAsState()
    val userId by viewModel.userId
    val teamId by viewModel.teamId
    
    LaunchedEffect(key1 = Unit) {
        viewModel.getMessages()
        viewModel.getTeam()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        LazyColumn {
            messageList.forEach { message ->
                item {
                    CustomMessageList(
                        userId = userId ?: 0,
                        messageDataItem = message,
                        onItemClick = {
                            var memberId = 0
                            if (userId == it.userId) {
                                sharedViewModel.setUerName(it.name2)
                                memberId = it.user2Id
                            } else {
                                sharedViewModel.setUerName(it.name1)
                                memberId = it.userId
                            }
                            navController.navigate("ChatScreen/$teamId/$memberId")
                        }
                    )
                }
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun MessagePreview() {
//    Message()
//}