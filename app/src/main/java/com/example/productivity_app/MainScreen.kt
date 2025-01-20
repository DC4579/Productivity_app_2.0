import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productivity_app.AuthViewModel
import com.example.productivity_app.Authstate
import com.example.productivity_app.Navitem
import com.example.productivity_app.pages.homePage
import com.example.productivity_app.pages.timerListPage



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController
) {
    // Initialize NavController
    val navController = rememberNavController()

    val authState by authViewModel.authState.observeAsState()

    // Define navigation items
    val navItemList = listOf(
        Navitem("Pomodoro", Icons.Default.Star),
        Navitem("To do list", Icons.Default.DateRange),
        Navitem("Dashboard", Icons.Default.Menu),
        Navitem("Profile", Icons.Default.AccountCircle),
    )

    // Track selected index for bottom navigation
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            when (index) {
                                0 -> navController.navigate("timerList") {
                                    popUpTo("home") { inclusive = true }
                                }
                                1 -> navController.navigate("toDoList") {
                                    popUpTo("home") { inclusive = false }
                                }
                                2 -> navController.navigate("home") {
                                    popUpTo("home") { inclusive = false }
                                }
                                else -> Unit
                            }
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Define NavHost for managing screens
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("timerList") {
                timerListPage(
                    modifier = modifier.padding(innerPadding),
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
            composable("toDoList") {
                timerListPage(
                    modifier = modifier.padding(innerPadding),
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
            composable("home") {
                homePage(modifier = modifier.padding(innerPadding),
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
            composable("profile") {
                Text("Profile Page") // Replace with your Profile composable
            }
        }
    }
    Button(onClick = {
        authViewModel.signout()  // Sign out the user
        // Navigation is handled by LaunchedEffect in MyAppNavigation based on authState
    }) {
        Text("Sign Out")
    }
    LaunchedEffect(authState) {
        if (authState is Authstate.Unauthenticated) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }
}

