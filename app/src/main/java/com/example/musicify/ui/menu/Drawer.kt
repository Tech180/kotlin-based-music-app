package com.example.musicify.ui.menu

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.musicify.ui.screens.HomeScreen
import com.example.musicify.ui.models.Screens
import com.example.musicify.ui.theme.MusicifyTheme
import androidx.navigation.compose.composable
import com.example.musicify.ui.screens.LibraryScreen


class DrawerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /*
        setContent {
            Drawer(rememberNavController())
        }
        */
        setContent {
            val navController = rememberNavController()

            val screen = listOf(
                Screens.Home,
                Screens.Library,
                Screens.Settings
            )

            MusicifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {

                        Box(Modifier.weight(1f)) {
                            NavHost(navController = navController, startDestination = Screens.Home.title.lowercase()) {
                                composable(Screens.Home.title.lowercase()) { HomeScreen() }
                                composable(Screens.Library.title.lowercase()) { LibraryScreen() }
                                composable(Screens.Settings.title.lowercase()) { /*SettingsScreen() */}
                            }
                        }

                        Navigation (screens = screen, navController = navController)                    }
                }
            }
        }
    }
}

/*
@Composable
fun Drawer(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf<Screen>(Screen.Home) }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            BottomNavigation {
                Screen.values().forEach { screen ->
                    BottomNavigationItem(
                        selected = selectedItem == screen,
                        onClick = {
                            navController.navigate(screen.title.lowercase())
                            selectedItem = screen
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedItem == screen) screen.activeIcon else screen.inactiveIcon,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        },
                        label = {
                            if (selectedItem == screen) {
                                Text(screen.title)
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Apply padding to the content based on innerPadding from Scaffold
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            // Navigate to appropriate composable based on selected tab
            NavHost(navController, startDestination = Screen.Home.title.lowercase()) {
                composable(Screen.Home.title.lowercase()) { HomeScreen() }
                composable(Screen.Library.title.lowercase()) { /* LibraryScreen() */ }
                composable(Screen.Settings.title.lowercase()) { /* SettingsScreen() */ }
            }
        }
    }
}
*/