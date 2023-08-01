package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.BearScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.bearNavGraph(navController: NavHostController, data: MainActivityViewModel) {

    navigation(
        route = Graph.BEAR,
        startDestination = BearScreen.Game.route
    ) {
        composable(route = BearScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_bear),
                imagePath = R.drawable.bear,
                imageDescription= "Bear",
                text = stringResource(R.string.station_bear_greeting_text),
                btnText = stringResource(R.string.station_bear_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(BearScreen.Game.route) }
            )
        }
        composable(route = BearScreen.Game.route) {
            BearScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = { navController.navigate(BearScreen.Bye.route) })
        }
        composable(route = BearScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_bear),
                imagePath = R.drawable.bear,
                imageDescription= "Bear",
                text = stringResource(R.string.station_bear_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class BearScreen(val route: String) {
    object Greeting : BearScreen(route = "BEAR_GREETING")
    object Game : BearScreen(route = "BEAR_GAME")
    object Bye : BearScreen(route = "BEAR_BYE")
}