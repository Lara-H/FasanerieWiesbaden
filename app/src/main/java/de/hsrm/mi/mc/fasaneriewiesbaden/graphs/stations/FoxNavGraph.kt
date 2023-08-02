package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.FoxScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.foxNavGraph(navController: NavHostController, data: MainActivityViewModel) {
    navigation(
        route = Graph.FOX,
        startDestination = FoxScreen.Greeting.route
    ) {
        composable(route = FoxScreen.Greeting.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_fox_greeting_text),
                btnText = stringResource(R.string.station_fox_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(FoxScreen.Game.route) }
            )
        }
        composable(route = FoxScreen.Game.route) {
            FoxScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = {
                    navController.navigate(FoxScreen.Bye.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                screenSize = data.screenSize,
            )
        }
        composable(route = FoxScreen.Bye.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_fox_bye_text),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class FoxScreen(val route: String) {
    object Greeting : FoxScreen(route = "FOX_GREETING")
    object Game : FoxScreen(route = "FOX_GAME")
    object Bye : FoxScreen(route = "FOX_BYE")
}