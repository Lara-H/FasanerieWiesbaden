package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.ChickenScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.chickenNavGraph(navController: NavHostController, data: MainActivityViewModel) {

    navigation(
        route = Graph.CHICKEN,
        startDestination = ChickenScreen.Game.route
    ) {
        composable(route = ChickenScreen.Greeting.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_chicken_greeting_text),
                btnText = stringResource(R.string.station_chicken_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(ChickenScreen.Game.route) }
            )
        }
        composable(route = ChickenScreen.Game.route) {
            ChickenScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = {
                    navController.navigate(ChickenScreen.Bye.route)  {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = ChickenScreen.Bye.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_chicken_bye_text),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class ChickenScreen(val route: String) {
    object Greeting : ChickenScreen(route = "CHICKEN_GREETING")
    object Game : ChickenScreen(route = "CHICKEN_GAME")
    object Bye : ChickenScreen(route = "CHICKEN_BYE")
}