package com.example.musicify.ui.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens (
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
) {
    data object Home : Screens("home", Icons.Filled.Home, Icons.Outlined.Home)
    data object Library : Screens("library", Icons.Filled.Person, Icons.Outlined.Person)
    data object Settings : Screens("settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}
