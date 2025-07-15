package com.example.taskomanagement.ui.screen.landingPage


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskomanagement.R
import com.example.taskomanagement.utils.Screen
import kotlinx.coroutines.delay

@Composable
fun LandingPage(navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        navController.navigate(Screen.MainScreen.routes) {
            popUpTo(Screen.LandingPageScreen.routes) { inclusive = true }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.tasko),
            contentDescription = stringResource(id = R.string.app_name),
        )
        Text(
            text = "Atur tugas-tugas Anda",
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
//        Button(
//            onClick = {
//                navController.popBackStack()
//                navController.navigate(Screen.MainScreen.routes)
//            },
//            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green_1)),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text(text = "Mulai")
//        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//    TaskOManagementTheme {
//        LandingPage()
//    }
//}