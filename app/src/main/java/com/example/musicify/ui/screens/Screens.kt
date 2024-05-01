package com.example.musicify.ui.screens

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
    data object Home : Screens("Home", Icons.Filled.Home, Icons.Outlined.Home)
    data object Library : Screens("Library", Icons.Filled.Person, Icons.Outlined.Person)
    data object Settings : Screens("Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}
