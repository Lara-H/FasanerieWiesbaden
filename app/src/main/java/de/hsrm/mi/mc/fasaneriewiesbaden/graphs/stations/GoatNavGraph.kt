package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.GoatScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.goatNavGraph(navController: NavHostController, data: MainActivityViewModel) {
    navigation(
        route = Graph.GOAT,
        startDestination = GoatScreen.Greeting.route
    ) {
        composable(route = GoatScreen.Greeting.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_goat_greeting_text),
                btnText = stringResource(R.string.station_goat_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(GoatScreen.Game.route) }
            )
        }
        composable(route = GoatScreen.Game.route) {
            GoatScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = {
                    navController.navigate(GoatScreen.Bye.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onFalseClick = { navController.navigate(GoatScreen.Error.route) }
            )
        }
        composable(route = GoatScreen.Error.route) {
            CommunicationScreen(
                data = data,
                text = stringResource(R.string.station_goat_game_error),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(GoatScreen.Game.route) }
            )
        }
        composable(route = GoatScreen.Bye.route) {
            CommunicationScreen(
                data = data,
                title = stringResource(R.string.title_name_goat),
                imagePath = R.drawable.goat,
                text = stringResource(R.string.station_goat_bye_text),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class GoatScreen(val route: String) {
    object Greeting : GoatScreen(route = "GOAT_GREETING")
    object Game : GoatScreen(route = "GOAT_GAME")
    object Bye : GoatScreen(route = "GOAT_BYE")
    object Error : GoatScreen(route = "GOAT_ERROR")
}