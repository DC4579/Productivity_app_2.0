package com.example.productivity_app.pages



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.productivity_app.AuthViewModel

@Composable
fun PomodoroTimerPage(modifier: Modifier = Modifier ,navController: NavController, authViewModel: AuthViewModel) {
    var timeLeft by remember { mutableStateOf(25 * 60) } // 25 minutes in seconds
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (timeLeft > 0) {
                kotlinx.coroutines.delay(1000L) // 1 second delay
                timeLeft -= 1
            }
            isRunning = false
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pomodoro Timer", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "${timeLeft / 60} : ${timeLeft % 60}", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { isRunning = !isRunning }) {
            Text(text = if (isRunning) "Pause" else "Start")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PomodoroTimerPagePreview() {
    val navController = rememberNavController() // Define the NavController for the preview
    PomodoroTimerPage(
        modifier = Modifier,
        navController = navController,
        authViewModel = AuthViewModel() // Provide an instance of AuthViewModel
    )
}
