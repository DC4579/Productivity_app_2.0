package com.example.productivity_app.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productivity_app.AuthViewModel
import com.example.productivity_app.Authstate

// Define Navitem class
data class Navitem(val label: String, val icon: ImageVector)

@Composable
fun homePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(authState) {
        if (authState is Authstate.Unauthenticated) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true } // Clears the back stack to prevent returning to the home screen
            }
        }
    }
    Scaffold(
        topBar = { CustomTopBar(authViewModel) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            MotivationalQuote()
            Spacer(modifier = Modifier.height(16.dp))
            QuickNavigationSection(navController)
            Spacer(modifier = Modifier.height(16.dp))
            PlayAmbientSounds()
            Spacer(modifier = Modifier.height(16.dp))
            SessionList(navController)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Page", fontSize = 32.sp)

        TextButton(onClick = { authViewModel.signout() }) {
            //Text("Sign out")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("timerList") }) {
            Text("Go to Timers")
        }
    }
    LaunchedEffect(authState) {
        if (authState is Authstate.Unauthenticated) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }
    @Composable
    fun CustomTopBar(authViewModel: AuthViewModel) {
        //TopAppBar(
            title = {
                Text(
                    "Productivity",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                IconButton(onClick = { authViewModel.signout() }) {
                    TextButton {
                        Text("Log Out")
                    }
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFE3FAE3))
        )
    }
}


