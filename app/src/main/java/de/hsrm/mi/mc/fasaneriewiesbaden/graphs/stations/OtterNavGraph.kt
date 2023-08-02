package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.OtterScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.otterNavGraph(navController: NavHostController, data: MainActivityViewModel) {

    navigation(
        route = Graph.OTTER,
        startDestination = OtterScreen.Greeting.route
    ) {
        composable(route = OtterScreen.Greeting.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_otter_greeting_text),
                btnText = stringResource(R.string.station_otter_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(OtterScreen.Game.route) }
            )
        }
        composable(route = OtterScreen.Game.route) {
            OtterScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = {
                    navController.navigate(OtterScreen.Bye.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                screenSize = data.screenSize,
            )
        }
        composable(route = OtterScreen.Bye.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_otter_bye_text),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class OtterScreen(val route: String) {
    object Greeting : OtterScreen(route = "OTTER_GREETING")
    object Game : OtterScreen(route = "OTTER_GAME")
    object Bye : OtterScreen(route = "OTTER_BYE")
}