package com.example.taskomanagement.ui.screen.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskomanagement.R
import com.example.taskomanagement.ui.theme.TaskOManagementTheme

@Composable
fun Login() {
    var name by remember { mutableStateOf("Nama lengkap") }
    var email by remember { mutableStateOf("Email") }
    var password by remember { mutableStateOf("Kata sandi") }
    var passwordConfirmation by remember { mutableStateOf("Konfirmasi kata sandi") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = (16.dp))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.tasko),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Text(
            text = "Buat Akun",
            fontSize = 18.sp,
            fontWeight = SemiBold
        )
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.grey),
                focusedContainerColor = colorResource(id = R.color.grey),
                unfocusedTextColor = colorResource(id = R.color.grey_1),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id =R.color.grey_1)
            ),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.person), contentDescription = "")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)

        )
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.grey),
                focusedContainerColor = colorResource(id = R.color.grey),
                unfocusedTextColor = colorResource(id = R.color.grey_1),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id =R.color.grey_1)
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
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.grey),
                focusedContainerColor = colorResource(id = R.color.grey),
                unfocusedTextColor = colorResource(id = R.color.grey_1),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id =R.color.grey_1)
            ),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)

        )
        TextField(
            value = passwordConfirmation,
            onValueChange = {
                password = it
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.grey),
                focusedContainerColor = colorResource(id = R.color.grey),
                unfocusedTextColor = colorResource(id = R.color.grey_1),
                focusedTextColor = colorResource(id = R.color.black),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.grey_1)
            ),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)

        )
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green_1)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
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
            Text(text = "Masuk", fontWeight = Bold, modifier = Modifier.padding(start = 4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TaskOManagementTheme {
        Login()
    }
}