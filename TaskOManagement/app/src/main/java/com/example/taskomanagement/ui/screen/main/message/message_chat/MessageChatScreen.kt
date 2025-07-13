package com.example.taskomanagement.ui.screen.main.message.message_chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.utils.convertMillisToTime
import org.koin.androidx.compose.koinViewModel

@Composable
fun Chat(
    memberId: Int,
    viewModel: MessageViewModel = koinViewModel(),
) {
    var text by remember { mutableStateOf("") }

    viewModel.setMemberId(memberId)

    LaunchedEffect(key1 = Unit) {
        viewModel.observeMessage()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 8.dp),
            userScrollEnabled = true,
            reverseLayout = true,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(viewModel.messages.reversed()) { message ->
                Row {
                    if (message.senderId != memberId) {
                        Spacer(modifier = Modifier.weight(1f))
                        TextSendCard(
                            text = message.message ?: "",
                            datetime = message.currentDate ?: 0L
                        )
                    } else {
                        TextReceiveCard(
                            text = message.message ?: "",
                            datetime = message.currentDate ?: 0L
                        )
                    }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(text = "Tulis pesan") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                shape = RoundedCornerShape(20.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    viewModel.sendMessage(text = text)
                    text = ""
                },
                enabled = text != "",
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Kirim pesan",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(30.dp)
                )
            }
        }
    }
}

@Composable
fun TextSendCard(text: String, datetime: Long) {
    val timestamp = convertMillisToTime(datetime)
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(top = 4.dp, bottom = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = timestamp,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Composable
fun TextReceiveCard(text: String, datetime: Long) {
    val timestamp = convertMillisToTime(datetime)
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(top = 4.dp, bottom = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = timestamp,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CharPreview() {
//    TextCard()
//}