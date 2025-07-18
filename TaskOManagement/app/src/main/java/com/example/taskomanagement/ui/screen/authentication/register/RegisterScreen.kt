package com.example.taskomanagement.ui.screen.authentication.register

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskomanagement.R
import com.example.taskomanagement.utils.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun Register(
    navController: NavController,
    viewModel: RegisterViewModel = koinViewModel(),
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirmationVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = (16.dp))
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.tasko),
            contentDescription = stringResource(id = R.string.app_name),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Buat Akun",
            fontSize = 18.sp,
            fontWeight = SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            placeholder = { Text(text = "Nama lengkap") },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            ),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.person), contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)

        )
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text(text = "Email") },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            ),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.email), contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)

        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "Passsword") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            ),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "") },
            trailingIcon = { IconButton(onClick = {
                passwordVisible = !passwordVisible
            }) {
                if (passwordVisible) {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility),
                        contentDescription = "Enable password"
                    )
                } else {
                    Icon(painter = painterResource(id = R.drawable.visibility_off),
                        contentDescription = "Disable password")
                }
            }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)

        )
        TextField(
            value = passwordConfirmation,
            onValueChange = {
                passwordConfirmation = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordConfirmationVisible) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = { Text(text = "Konfirmasi passsword") },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = colorResource(id = R.color.black),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            ),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "") },
            trailingIcon = { IconButton(onClick = {
                passwordConfirmationVisible = !passwordConfirmationVisible
            }) {
                if (passwordConfirmationVisible) {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility),
                        contentDescription = "Enable password"
                    )
                } else {
                    Icon(painter = painterResource(id = R.drawable.visibility_off),
                        contentDescription = "Disable password")
                   }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)

        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            enabled = viewModel.registerValidation(name, email, password, passwordConfirmation),
            onClick = {
                coroutineScope.launch {
                    try {
                        if (viewModel.register(name, email, password).status) {
                            viewModel.firebaseAuth(email, password)
                            navController.navigate(Screen.RegisterSuccessScreen.routes)
                        } else {
                            Log.d("REGISTER HAS FAILED", "Email sudah terpakai")
                        }
                    } catch (e: Exception) {
                        Log.d("REGISTER HAS FAILED", "${e.message}")
                    }
                }
            },
            colors = if (viewModel.registerValidation(name, email, password, passwordConfirmation)) {
                ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            } else {
                ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)

        ) {
            Text(text = "Daftar")
        }
        Text(text = "Atau")
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
        }
        Row {
            Text(text = "Sudah punya akun?")
            Text(
                text = "Masuk",
                fontWeight = Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable { navController.navigate(Screen.LoginScreen.routes) }
                    .padding(start = 4.dp),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
    OnBackPressed()
}

//@Preview(showBackground = true)
//@Composable
//fun LoginPreview() {
//    TaskOManagementTheme {
//        Register()
//    }
//}

@Composable
private fun OnBackPressed() {
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finish()
    }
}
