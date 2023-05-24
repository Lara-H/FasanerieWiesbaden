package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.INTRO,
    ) {
        introNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val INTRO = "intro_graph"
}