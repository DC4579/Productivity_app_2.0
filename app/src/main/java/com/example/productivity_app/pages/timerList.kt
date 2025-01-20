package com.example.productivity_app.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.productivity_app.AuthViewModel

@Composable
fun timerListPage(modifier: Modifier = Modifier,navController: NavController, authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select a Timer", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Pomodoro Timer",
            modifier = Modifier.clickable { navController.navigate("pomodoroTimer") }.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Short Break Timer",
            modifier = Modifier.clickable { navController.navigate("shortBreakTimer") }.padding(8.dp)
        )
    }
}
@Preview
    (showBackground = true)
@Composable fun TimerListPagePreview()
{ val navController = rememberNavController()
    timerListPage(modifier = Modifier, navController = navController, authViewModel = AuthViewModel()) }

