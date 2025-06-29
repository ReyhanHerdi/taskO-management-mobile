package com.example.taskomanagement.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDial(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    Dialog(
        onDismissRequest = { onDismiss() })
    {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    periodSelectorBorderColor = MaterialTheme.colorScheme.primary
                )
            )
            Button(
                onClick = { onConfirm(timePickerState) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.background,
                    disabledContentColor = MaterialTheme.colorScheme.primaryContainer,
                ),

                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            ) {
                Text(text = "Konfirmasi")
            }
        }
    }
}