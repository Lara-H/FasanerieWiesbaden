package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.DeerScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.deerNavGraph(navController: NavHostController, data: Data) {

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
                onBtnClick = { navController.navigate(DeerScreen.Game.route) }
            )
        }
        composable(route = DeerScreen.Game.route) {
            DeerScreen()
        }
        composable(route = DeerScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_deer),
                imagePath = R.drawable.deer,
                imageDescription= "Deer",
                text = stringResource(R.string.station_deer_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(Graph.MAIN) {
                    data.listStationsState.value[data.currentNumberState.value].isDone = true
                    data.currentNumberState.value++
                    data.currentStationState.value = data.listStationsState.value[data.currentNumberState.value]
                    }
                }
            )
        }
    }
}

sealed class DeerScreen(val route: String) {
    object Greeting : DeerScreen(route = "DEER_GREETING")

    object Game : DeerScreen(route = "DEER_GAME")
    object Bye : DeerScreen(route = "DEER_BYE")
}