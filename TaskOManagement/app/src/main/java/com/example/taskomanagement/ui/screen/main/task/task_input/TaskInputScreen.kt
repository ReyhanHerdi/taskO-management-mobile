package com.example.taskomanagement.ui.screen.main.task.task_input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.R
import com.example.taskomanagement.utils.DatePickerModal
import com.example.taskomanagement.utils.TimePickerDial
import com.example.taskomanagement.utils.convertMillisToDate
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInput(
    projectId: Int,
    navController: NavController,
    viewModel: TaskInputViewModel = koinViewModel(),
) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskDueDate by remember { mutableStateOf("") }
    var taskDueTime by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }


    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = {
                taskDueDate = convertMillisToDate(it)
            },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showTimePicker) {
        TimePickerDial(
            onConfirm = { time ->
                taskDueTime = "${time.hour}:${time.minute}:59"
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        OutlinedTextField(
            value = taskName,
            onValueChange = {
                taskName = it
            },
            label = { Text(text = "Judul Tugas") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Judul Tugas") },
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
            value = taskDescription,
            onValueChange = {
                taskDescription = it
            },
            label = { Text(text = "Deskripsi Tugas") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Deskripsi Tugas") },
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
            value = taskDueDate,
            onValueChange = {
                taskDueDate = it
            },
            label = { Text(text = "Tenggat Proyek (Tanggal)") },
            readOnly = true,
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
        OutlinedTextField(
            value = taskDueTime,
            onValueChange = {
                taskDueTime = it
            },
            label = { Text(text = "Tenggat Proyek (Pukul)") },
            readOnly = true,
            placeholder = { Text(text = "hh:mm:ss") },
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
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_access_time_24),
                            contentDescription = "Pilih pukul tenggat tugas",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = { showTimePicker = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )
        Button(
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContentColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            elevation = ButtonDefaults.buttonElevation(3.dp),
            enabled = taskName.isNotEmpty() && taskDueDate.isNotEmpty() && taskDueTime.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = {
                try {
                    viewModel.postTask(
                        projectId = projectId,
                        nameTask = taskName,
                        descriptionTask = taskDescription,
                        dueDateTask = taskDueDate,
                        dueTimeTask = taskDueTime
                    )
                } finally {
                    navController.popBackStack()
                }
            },
        ) {
            Text(
                text = "Buat Tugas"
            )
        }
    }
}