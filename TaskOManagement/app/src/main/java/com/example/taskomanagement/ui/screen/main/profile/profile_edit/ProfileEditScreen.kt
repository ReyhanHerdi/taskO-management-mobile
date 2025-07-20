package com.example.taskomanagement.ui.screen.main.profile.profile_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.utils.ShowCircularLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileEdit(
    viewModel: ProfileEditViewModel = koinViewModel(),
) {
    val user = viewModel.user.collectAsState()
    val loadingStatus = viewModel.dataResult.value
    val email = user.value.email ?: "Tanpa Email"
    val name = user.value.name ?: "Tanpa Nama"
    var username by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.getUser()
    }

    LaunchedEffect(key1 = user.value.name) {
        if (!user.value.name.isNullOrBlank()) {
            username = name
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Foto profil",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = { Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icon nama pengguna"
            )},
            trailingIcon = { Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit nama pengguna"
            )},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.tertiary,
                unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.tertiary,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = { Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Icon email pengguna"
            )},
//            trailingIcon = { Icon(
//                imageVector = Icons.Default.Email,
//                contentDescription = "Edit nama pengguna"
//            )},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.tertiary,
                unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.tertiary,
            ),
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
//        Spacer(modifier = Modifier.height(16.dp))
//        TextField(
//            value = "Password Pengguna",
//            onValueChange = {},
//            textStyle = MaterialTheme.typography.bodyMedium,
//            leadingIcon = { Icon(
//                imageVector = Icons.Default.Lock,
//                contentDescription = "Icon password pengguna"
//            )},
//            trailingIcon = { Icon(
//                imageVector = Icons.Default.Email,
//                contentDescription = "Edit nama pengguna"
//            )},
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.Transparent,
//                focusedContainerColor = Color.Transparent,
//                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
//                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
//                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
//                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary,
//                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
//                unfocusedTrailingIconColor = MaterialTheme.colorScheme.tertiary,
//                unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
//                focusedTextColor = MaterialTheme.colorScheme.tertiary,
//            ),
//            readOnly = true,
//            modifier = Modifier.fillMaxWidth()
//        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                viewModel.updateUser(
                    userName = username ?: ""
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Simpan"
            )
        }
    }
    when(loadingStatus) {
        is Result.Loading -> ShowCircularLoading(isLoading = true)
        is Result.Success -> ShowCircularLoading(isLoading = false)
        is Result.Error -> ShowCircularLoading(isLoading = false)
        null -> { /* DO NOTHING */ }
    }
}