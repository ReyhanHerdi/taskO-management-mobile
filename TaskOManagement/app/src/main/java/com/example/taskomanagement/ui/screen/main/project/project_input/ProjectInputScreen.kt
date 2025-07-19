package com.example.taskomanagement.ui.screen.main.project.project_input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.R
import com.example.taskomanagement.utils.DatePickerModal
import com.example.taskomanagement.utils.Screen
import com.example.taskomanagement.utils.convertMillisToDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectInput(
    teamId: Int,
    navController: NavController,
    viewModel: ProjectInputViewModel = koinViewModel(),
) {
    var projectName by remember { mutableStateOf("") }
    var projectdescription by remember { mutableStateOf("") }
    var projectDue by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        OutlinedTextField(
            value = projectName,
            onValueChange = {
                projectName = it
            },
            label = { Text(text = "Nama Proyek") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Nama Proyek") },
            shape = RoundedCornerShape(0.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = projectdescription,
            onValueChange = {
                projectdescription = it
            },
            label = { Text(text = "Deskripsi Proyek") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Deskripsi Proyek") },
            shape = RoundedCornerShape(0.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = projectDue,
            onValueChange = {
                projectDue = it
            },
            label = { Text(text = "Tenggat Waktu Proyek") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "YYYY-MM-DD") },
            shape = RoundedCornerShape(0.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            ),
            trailingIcon = {
                IconButton(
                    content = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Pilih tanggal tenggat waktu",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = { showDatePicker = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )
        if (showDatePicker) {
            DatePickerModal(
                onDateSelected = {
                    projectDue = convertMillisToDate(it)
                },
                onDismiss = { showDatePicker = false }
            )
        }
        Button(
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContentColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            elevation = ButtonDefaults.buttonElevation(3.dp),
            enabled = projectName.isNotEmpty() && projectDue.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = {
                try {
                    viewModel.postProject(
                        teamId = teamId,
                        nameProject = projectName,
                        descriptionProject = projectdescription,
                        dueProject = projectDue
                    )
                } finally {
                    navController.popBackStack(Screen.ProjectChooseTeamScreen.routes, true)
                }
            },
        ) {
            Text(
                text = "Buat Proyek"
            )
        }
    }
}