package com.example.taskomanagement.ui.cutom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.taskomanagement.data.response.MessageDataItem
import com.example.taskomanagement.imageBaseUrl
import com.example.taskomanagement.utils.formatDateTime

@Composable
fun CustomMessageList(
    userId: Int,
    messageDataItem: MessageDataItem,
    onItemClick: (MessageDataItem) -> Unit
) {
     var userName = if (messageDataItem.user2Id == userId) messageDataItem.name1 else messageDataItem.name2
     var photoUrl = if (messageDataItem.user2Id == userId) messageDataItem.photoUrl else messageDataItem.photo2Url
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable { onItemClick(messageDataItem) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            if (photoUrl == null) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Foto profil",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(50.dp)
                )
            } else {
                AsyncImage(
                    model = "${imageBaseUrl}/${photoUrl}",
                    contentDescription = "Foto profil user",
                    modifier = Modifier
                        .size(width = 50.dp, height = 50.dp)
                        .clip(shape = RoundedCornerShape(50.dp))
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
            ) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = messageDataItem.lastMessage ?: "Belum ada pesan",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = formatDateTime(messageDataItem.updatedAt),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }

    }
}

//@Composable
//@Preview(showBackground = true)
//fun CustomMessageListPreview() {
//    CustomMessageList()
//}