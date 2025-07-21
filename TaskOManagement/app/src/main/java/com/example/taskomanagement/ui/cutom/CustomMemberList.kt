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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.taskomanagement.data.response.MemberOfTeamDataItem
import com.example.taskomanagement.imageBaseUrl

@Composable
fun CustomMemberList(
    member: MemberOfTeamDataItem? = null,
    onItemClick: () -> Unit,
    userId: Int
) {
    val photoUrl = member?.user?.get(0)?.photoUrl
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable(enabled = member?.userId != userId) { onItemClick() }
    ) {
        Row {
            if (photoUrl == null) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Foto profil",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp)
                        .size(width = 50.dp, height = 50.dp)
                )
            } else {
                AsyncImage(
                    model = "$imageBaseUrl/$photoUrl",
                    contentDescription = "Foto profil",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp)
                        .size(width = 50.dp, height = 50.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "${member?.user?.get(0)?.name}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = if (member?.role == "leader") "Ketua" else "Anggota",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (member?.userId != userId) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Kirim pesan",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CustomMemberListPreview() {
//    CustomMemberList()
//}