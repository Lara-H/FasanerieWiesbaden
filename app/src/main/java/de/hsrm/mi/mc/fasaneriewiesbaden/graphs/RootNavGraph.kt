package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.MainScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.goatNavGraph

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.INTRO,
    ) {
        introNavGraph(navController = navController)
        goatNavGraph(navController = navController)
        composable(route = Graph.MAIN) {
            MainScreen()
        }
        outroNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val INTRO = "intro_graph"
    const val GOAT = "goat_graph"
    const val MAIN = "main_graph"
    const val OUTRO = "outro_graph"
}