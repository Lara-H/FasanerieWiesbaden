package de.hsrm.mi.mc.fasaneriewiesbaden

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(val route: String, val icon: ImageVector) {
    object Map: BottomBarScreen(route = "map_screen", icon = Icons.Default.Place)
    object Level: BottomBarScreen(route = "level_screen", icon = Icons.Default.Home)
    object Info: BottomBarScreen(route = "info_screen", icon = Icons.Default.Info)
}
