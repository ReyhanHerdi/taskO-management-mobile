package com.example.taskomanagement.utils

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import java.io.File

class GetContentActivityResult(
    private val launcher: ManagedActivityResultLauncher<String, Uri?>,
    val uri: Uri?
) {
    fun launch(mimeType: String) {
        launcher.launch(mimeType)
    }
}

@Composable
fun mediaPicker() : GetContentActivityResult {
    var uri by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {
        uri = it
    })
    return remember(launcher, uri) {
        GetContentActivityResult(launcher, uri)
    }
}

fun uploadFile(uri: Uri?, context: Context): File {
    val fileName: String = "photo_" + System.currentTimeMillis() + ".jpg"
    val file = File(context.filesDir, fileName)
    file.createNewFile()
    if (uri != null) {
        context.contentResolver.openInputStream(uri)?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }
    return file
}