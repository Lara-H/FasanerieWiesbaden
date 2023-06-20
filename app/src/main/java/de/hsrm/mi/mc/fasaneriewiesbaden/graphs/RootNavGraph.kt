package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.batNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.bearNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.chickenNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.deerNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.foxNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.MainScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.goatNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.lynxNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.otterNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.owlNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations.raccoonNavGraph

@Composable
fun RootNavGraph(navController: NavHostController, data: Data) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.FOX,
    ) {
        introNavGraph(navController = navController, data = data)

        goatNavGraph(navController = navController)
        foxNavGraph(navController = navController)
        bearNavGraph(navController = navController)
        lynxNavGraph(navController = navController)
        deerNavGraph(navController = navController)
        raccoonNavGraph(navController = navController)
        owlNavGraph(navController = navController)
        chickenNavGraph(navController = navController)
        otterNavGraph(navController = navController)
        batNavGraph(navController = navController)

        composable(route = Graph.RACCOON) {
            MainScreen(data = data)
        }
        outroNavGraph(navController = navController, data = data)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val INTRO = "intro_graph"
    const val GOAT = "goat_graph"
    const val FOX = "fox_graph"
    const val BEAR = "bear_graph"
    const val LYNX = "lynx_graph"
    const val DEER = "deer_graph"
    const val RACCOON = "raccoon_graph"
    const val OWL = "owl_graph"
    const val CHICKEN = "chicken_graph"
    const val OTTER = "otter_graph"
    const val BAT = "bat_graph"
    const val MAIN = "main_graph"
    const val OUTRO = "outro_graph"
}