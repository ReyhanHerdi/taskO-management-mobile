package com.example.taskomanagement.ui.screen.authentication.login

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.utils.Screen
import com.example.taskomanagement.utils.ShowCircularLoading
import com.example.taskomanagement.utils.showToast
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val authStatus by viewModel.auth.collectAsState()
    val loginStatus = viewModel.loginResult.value
    val context = LocalContext.current
    val message by viewModel.message

    LaunchedEffect(key1 = authStatus) {
        Log.d("AUTH CHECK", authStatus.toString())
        if (authStatus) {
            navController.popBackStack()
            navController.navigate(Screen.MainScreen.routes)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = (16.dp))
            .systemBarsPadding()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.tasko),
            contentDescription = stringResource(id = R.string.app_name),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Masuk ke Akun",
            fontSize = 18.sp,
            fontWeight = SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
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
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.email), contentDescription = "")},
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
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "")},
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
            }},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)

        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                scope.launch {
                    viewModel.login(email, password)
                    showToast("$message", context)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(text = "Masuk")
        }
        Text(text = "Atau")
        Button(
            onClick = {
                Log.d("AUTH STATUS", authStatus.toString())
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
            Text(text = "Belum punya akun?")
            Text(
                text = "Daftar",
                fontWeight = Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { navController.navigate(Screen.RegisterScreen.routes) }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
    when(loginStatus) {
        is Result.Loading -> ShowCircularLoading(isLoading = true)
        is Result.Success -> ShowCircularLoading(isLoading = false)
        is Result.Error -> ShowCircularLoading(isLoading = false)
        null -> {
            // DO NOTHING
        }
    }
    OnBackPressed()
}

//@Preview(showBackground = true)
//@Composable
//fun LoginPreview() {
//    TaskOManagementTheme {
//        Login()
//    }
//}

@Composable
private fun OnBackPressed() {
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finish()
    }
}
