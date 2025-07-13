package com.example.taskomanagement.ui.screen.main.message.message_chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Chat() {
    var text by remember { mutableStateOf("") }
    val textList = remember { mutableStateListOf<String>() }
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
            for (message in textList.reversed()) {
                item {
                    TextCard(message)
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
            IconButton(onClick = {
                textList.add(text)
                text = ""
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Kirim pesan",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(30.dp)
                )
            }
        }
    }
}

@Composable
fun TextCard(text: String) {
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
                text = "Ini waktu",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CharPreview() {
//    TextCard()
//}