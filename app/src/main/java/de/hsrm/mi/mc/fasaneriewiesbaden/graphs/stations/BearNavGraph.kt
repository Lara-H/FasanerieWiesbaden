package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.bearNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.BEAR,
        startDestination = BearScreen.Greeting.route
    ) {
        composable(route = BearScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_bear),
                imagePath = R.drawable.bear,
                imageDescription= "Bear",
                text = stringResource(R.string.station_bear_greeting_text),
                btnText = stringResource(R.string.station_bear_greeting_btn),
                onBtnClick = { }
            )
        }
        composable(route = BearScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_bear),
                imagePath = R.drawable.bear,
                imageDescription= "Bear",
                text = stringResource(R.string.station_bear_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(Graph.MAIN) }
            )
        }
    }
}

sealed class BearScreen(val route: String) {
    object Greeting : BearScreen(route = "GREETING")
    object Bye : BearScreen(route = "BYE")
}