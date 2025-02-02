package com.example.taskomanagement.ui.screen.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.material3.MaterialTheme as MaterialTheme1

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "User's picture",
                    modifier = Modifier
                        .size(width = 70.dp, height = 70.dp)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Text(
                        text = "Jane Doe",
                        style = MaterialTheme1.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "janedoe@mail.com",
                        style = MaterialTheme1.typography.titleSmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit profile",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(7.dp),
                modifier = Modifier.size(width = 100.dp, height = 80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "20",
                        style = MaterialTheme1.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Tugas",
                        style = MaterialTheme1.typography.labelMedium
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(7.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(width = 100.dp, height = 80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "7",
                        style = MaterialTheme1.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Proyek",
                        style = MaterialTheme1.typography.labelMedium,
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(7.dp),
                modifier = Modifier
                    .size(width = 100.dp, height = 80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "5",
                        style = MaterialTheme1.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Tim",
                        style = MaterialTheme1.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile()
}