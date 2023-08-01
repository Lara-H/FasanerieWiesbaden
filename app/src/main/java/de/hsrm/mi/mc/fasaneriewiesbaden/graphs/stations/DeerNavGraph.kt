package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.DeerScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.deerNavGraph(navController: NavHostController, data: MainActivityViewModel) {

    navigation(
        route = Graph.DEER,
        startDestination = DeerScreen.Greeting.route
    ) {
        composable(route = DeerScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_deer),
                imagePath = R.drawable.deer,
                imageDescription= "Deer",
                text = stringResource(R.string.station_deer_greeting_text),
                btnText = stringResource(R.string.station_deer_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(DeerScreen.Game.route) }
            )
        }
        composable(route = DeerScreen.Game.route) {
            DeerScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = { navController.navigate(DeerScreen.Bye.route) }
            )
        }
        composable(route = DeerScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_deer),
                imagePath = R.drawable.deer,
                imageDescription= "Deer",
                text = stringResource(R.string.station_deer_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() }
            )
        }
    }
}

sealed class DeerScreen(val route: String) {
    object Greeting : DeerScreen(route = "DEER_GREETING")

    object Game : DeerScreen(route = "DEER_GAME")
    object Bye : DeerScreen(route = "DEER_BYE")
}