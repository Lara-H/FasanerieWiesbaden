package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.batNavGraph(navController: NavHostController) {

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
                onBtnClick = { }
            )
        }
        composable(route = BatScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_bat),
                imagePath = R.drawable.bat,
                imageDescription= "Bat",
                text = stringResource(R.string.station_bat_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(Graph.MAIN) }
            )
        }
    }
}

sealed class BatScreen(val route: String) {
    object Greeting : BatScreen(route = "GREETING")
    object Bye : BatScreen(route = "BYE")
}