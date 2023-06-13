package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.owlNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.OWL,
        startDestination = OwlScreen.Greeting.route
    ) {
        composable(route = OwlScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_owl),
                imagePath = R.drawable.owl,
                imageDescription= "Owl",
                text = stringResource(R.string.station_owl_greeting_text),
                btnText = stringResource(R.string.station_owl_greeting_btn),
                onBtnClick = { }
            )
        }
        composable(route = OwlScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_owl),
                imagePath = R.drawable.owl,
                imageDescription= "Owl",
                text = stringResource(R.string.station_owl_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(Graph.MAIN) }
            )
        }
    }
}

sealed class OwlScreen(val route: String) {
    object Greeting : OwlScreen(route = "GREETING")
    object Bye : OwlScreen(route = "BYE")
}