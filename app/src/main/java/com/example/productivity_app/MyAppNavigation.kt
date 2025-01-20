package com.example.productivity_app

import MainScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productivity_app.pages.loginPage
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.productivity_app.pages.PomodoroTimerPage
import com.example.productivity_app.pages.homePage
import com.example.productivity_app.pages.signUpPage
import com.example.productivity_app.pages.timerListPage

@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()

    val authState by authViewModel.authState.observeAsState(Authstate.Unauthenticated)


    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            loginPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            signUpPage(modifier, navController, authViewModel)
        }
        composable("home") {
            MainScreen(
                modifier = modifier,
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("timerList") {
            timerListPage(modifier, navController, authViewModel)
        }
        composable("toDoList") {
            PomodoroTimerPage(modifier, authViewModel)
        }
    }

// Redirect to login screen if user is unauthenticated
LaunchedEffect(authState) {
    if (authState is Authstate.Unauthenticated) {
        navController.navigate("login") {
            popUpTo("home") { inclusive = true }
        }
    }
}
}
//package com.example.productivity_app
//
//import MainScreen
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.productivity_app.pages.loginPage
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
//import com.example.productivity_app.pages.PomodoroTimerPage
//import com.example.productivity_app.pages.homePage
//import com.example.productivity_app.pages.signUpPage
//import com.example.productivity_app.pages.timerListPage

//@Composable
//fun MyAppNavigation(
//    modifier: Modifier = Modifier,
//    authViewModel: AuthViewModel
//) {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "login"
//    ) {
//        composable("login") {
//            loginPage(modifier, navController, authViewModel)
//        }
//        composable("signup") {
//            signUpPage(modifier, navController, authViewModel)
//        }
//        composable("home") {
//            MainScreen(
//                modifier = modifier,
//                navController = navController,
//                authViewModel = authViewModel
//            )
//        }
//        composable("timerList") {
//            timerListPage(modifier, navController, authViewModel)
//        }
//        composable("toDoList") {
//            PomodoroTimerPage(modifier, authViewModel)
//        }
//    }
//}