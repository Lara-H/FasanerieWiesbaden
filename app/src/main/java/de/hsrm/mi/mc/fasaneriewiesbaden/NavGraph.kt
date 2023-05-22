package de.hsrm.mi.mc.fasaneriewiesbaden

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Map.route
    ) {
        composable(
            route = Screen.Map.route
        ) {
            MapScreen()
        }
        composable(
            route = Screen.Level.route
        ) {
            LevelScreen()
        }
        composable(
            route = Screen.Info.route
        ) {
            InfoScreen()
        }
    }
}