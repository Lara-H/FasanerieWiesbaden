package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.hsrm.mi.mc.fasaneriewiesbaden.LocationDetails
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.InfoScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.LevelScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.MapScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

@Composable
fun MainNavGraph(navController: NavHostController, mainNavController: NavHostController, data: MainActivityViewModel, currentLocation: LocationDetails) {
    NavHost(
        navController = mainNavController,
        route = Graph.MAIN,
        startDestination = MainScreen.Map.route
    ) {
        composable(
            route = MainScreen.Map.route
        ) {
            MapScreen(
                navController = navController,
                data = data,
                currentLocation = currentLocation
            )
        }
        composable(
            route = MainScreen.Level.route
        ) {
            LevelScreen(data = data)
        }
        composable(
            route = MainScreen.Info.route
        ) {
            InfoScreen()
        }
    }
}

sealed class MainScreen(val route: String, val icon: Int) {
    object Map: MainScreen(route = "map_screen", icon = R.drawable.icon_map)
    object Level: MainScreen(route = "level_screen", icon = R.drawable.icon_level)
    object Info: MainScreen(route = "info_screen", icon = R.drawable.icon_info)
}