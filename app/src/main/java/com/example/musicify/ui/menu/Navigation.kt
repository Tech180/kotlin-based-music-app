package com.example.musicify.ui.menu

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.musicify.ui.screens.Screens

@Composable
fun Navigation (
    screens: List<Screens>
) {
    var selectedScreen by remember { mutableStateOf(0) }
    Box(
        Modifier
            .shadow(5.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .height(64.dp)
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (screen in screens) {
                val isSelected = screen == screens[selectedScreen]

                val weight by animateFloatAsState(
                    targetValue = if (isSelected) 1.5f else 1f, label = ""
                )

                Box(
                    modifier = Modifier.weight(weight),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    val interactionSource = remember {
                        MutableInteractionSource()
                    }
                    DrawerItem (
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            selectedScreen = screens.indexOf(screen)
                        },
                        screen = screen,
                        isSelected = isSelected
                    )
                }
            }
        }
    }
}

/*
@Composable
fun NavigationHost(navController: NavController) {
    NavHost(navController, startDestination = Screens.Home.title.lowercase()) {
        composable(Screens.Home.title.lowercase()) { HomeScreen() }
        composable(Screens.Library.title.lowercase()) { /* LibraryScreen() */ }
        composable(Screens.Settings.title.lowercase()) { /* SettingsScreen() */ }
    }
}
 */