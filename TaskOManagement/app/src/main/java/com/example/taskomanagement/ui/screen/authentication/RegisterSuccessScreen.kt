package com.example.taskomanagement.ui.screen.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskomanagement.R
import com.example.taskomanagement.ui.theme.TaskOManagementTheme
import com.example.taskomanagement.utils.Screen

@Composable
fun RegisterSuccess(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()

    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.reg_success),
            contentDescription = "Register success",
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "Akun berhasil dibuat",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Masuk untuk mulai mengatur tugas-tugasmu",
            fontSize = 12.sp,
            color = colorResource(id = R.color.grey_2),
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(214.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.LoginScreen.routes)
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green_1)),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
            ) {
            Text(text = "Masuk")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RegisterSuccessPreview() {
//    TaskOManagementTheme {
//        RegisterSuccess()
//    }
//}