package de.hsrm.mi.mc.fasaneriewiesbaden

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Map.route
    ) {
        composable(
            route = BottomBarScreen.Map.route
        ) {
            MapScreen()
        }
        composable(
            route = BottomBarScreen.Level.route
        ) {
            LevelScreen()
        }
        composable(
            route = BottomBarScreen.Info.route
        ) {
            InfoScreen()
        }
    }
}