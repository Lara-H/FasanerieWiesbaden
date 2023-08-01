package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.BatScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.batNavGraph(navController: NavHostController, data: MainActivityViewModel) {

    navigation(
        route = Graph.BAT,
        startDestination = BatScreen.Greeting.route
    ) {
        composable(route = BatScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_bat),
                imagePath = R.drawable.bat,
                imageDescription= "Bat",
                text = stringResource(R.string.station_bat_greeting_text),
                btnText = stringResource(R.string.station_bat_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(BatScreen.Game.route) }
            )
        }
        composable(route = BatScreen.Game.route) {
            BatScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = {
                    navController.navigate(BatScreen.Bye.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = BatScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_bat),
                imagePath = R.drawable.bat,
                imageDescription= "Bat",
                text = stringResource(R.string.station_bat_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class BatScreen(val route: String) {
    object Greeting : BatScreen(route = "BAT_GREETING")
    object Game : BatScreen(route = "BAT_GAME")
    object Bye : BatScreen(route = "BAT_BYE")
}