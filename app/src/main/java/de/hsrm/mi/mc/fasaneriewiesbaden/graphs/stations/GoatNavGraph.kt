package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.GoatScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.goatNavGraph(navController: NavHostController, data: Data) {
    navigation(
        route = Graph.GOAT,
        startDestination = GoatScreen.Greeting.route
    ) {
        composable(route = GoatScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat),
                imagePath = R.drawable.goat,
                imageDescription= "Goat",
                text = stringResource(R.string.station_goat_greeting_text),
                btnText = stringResource(R.string.station_goat_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(GoatScreen.Game.route) }
            )
        }
        composable(route = GoatScreen.Game.route) {
            GoatScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = { navController.navigate(GoatScreen.Bye.route) },
                onFalseClick = { navController.navigate(GoatScreen.Error.route) }
            )
        }
        composable(route = GoatScreen.Error.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat),
                imagePath = R.drawable.goat,
                imageDescription= "Goat",
                text = stringResource(R.string.station_goat_game_error),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(GoatScreen.Game.route) }
            )
        }
        composable(route = GoatScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat),
                imagePath = R.drawable.goat,
                imageDescription= "Goat",
                text = stringResource(R.string.station_goat_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(Graph.MAIN) }
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