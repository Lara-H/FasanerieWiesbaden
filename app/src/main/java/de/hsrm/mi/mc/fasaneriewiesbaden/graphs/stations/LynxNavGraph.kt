package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.LynxScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.lynxNavGraph(navController: NavHostController, data: MainActivityViewModel) {

    navigation(
        route = Graph.LYNX,
        startDestination = LynxScreen.Greeting.route
    ) {
        composable(route = LynxScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_lynx),
                imagePath = R.drawable.lynx,
                imageDescription= "Lynx",
                text = stringResource(R.string.station_lynx_greeting_text),
                btnText = stringResource(R.string.station_lynx_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(LynxScreen.Game.route) }
            )
        }
        composable(route = LynxScreen.Game.route) {
            LynxScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = { navController.navigate(LynxScreen.Bye.route) }
            )
        }
        composable(route = LynxScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_lynx),
                imagePath = R.drawable.lynx,
                imageDescription= "Lynx",
                text = stringResource(R.string.station_lynx_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class LynxScreen(val route: String) {
    object Greeting : LynxScreen(route = "LYNX_GREETING")
    object Game : LynxScreen(route = "LYNX_GAME")
    object Bye : LynxScreen(route = "LYNX_BYE")
}