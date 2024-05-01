package com.example.musicify.ui.menu

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.musicify.ui.screens.Screens
import com.example.musicify.ui.theme.MusicifyTheme

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
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Navigation (screens = screen)
                    }
                }
            }
        }
    }
}

/*
@Composable
private fun Drawer(
    modifier: Modifier = Modifier,
    screen: Screens,
    isSelected: Boolean,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(if (isSelected) 36.dp else 26.dp)
                .shadow(
                    elevation = if (isSelected) 15.dp else 0.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(20.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                rememberVectorPainter(
                    image = if (isSelected) screen.activeIcon else screen.inactiveIcon
                ),
                contentDescription = screen.title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .padding(start = 11.dp)
                    .alpha(if (isSelected) 1f else .5f)
                    .size(if (isSelected) 26.dp else 20.dp),
            )

            if (isSelected) {
                Text(
                    text = screen.title,
                    modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                    maxLines = 1,
                )
            }
        }
    }
}
 */

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