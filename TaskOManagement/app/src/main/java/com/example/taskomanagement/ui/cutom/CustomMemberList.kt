package com.example.taskomanagement.ui.cutom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.data.response.MemberItem
import com.example.taskomanagement.data.response.MemberOfTeamDataItem
import com.example.taskomanagement.data.response.UserItem

@Composable
fun CustomMemberList(
    member: MemberOfTeamDataItem? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
//            .clickable { onItemClick(projects) }
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Foto profil",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
                    .size(width = 50.dp, height = 50.dp)
            )
            Column {
                Text(
                    text = "${member?.user?.get(0)?.name}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = member?.role ?: "Member",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomMemberListPreview() {
    CustomMemberList()
}