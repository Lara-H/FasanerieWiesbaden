package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.FoxScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.foxNavGraph(navController: NavHostController, data: Data) {

    navigation(
        route = Graph.FOX,
        startDestination = FoxScreen.Greeting.route
    ) {
        composable(route = FoxScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_fox),
                imagePath = R.drawable.fox,
                imageDescription= "Fox",
                text = stringResource(R.string.station_fox_greeting_text),
                btnText = stringResource(R.string.station_fox_greeting_btn),
                onBtnClick = { navController.navigate(FoxScreen.Game.route) }
            )
        }
        composable(route = FoxScreen.Game.route) {
            FoxScreen()
        }
        composable(route = FoxScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_fox),
                imagePath = R.drawable.fox,
                imageDescription= "Fox",
                text = stringResource(R.string.station_fox_bye_text),
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

sealed class FoxScreen(val route: String) {
    object Greeting : FoxScreen(route = "GREETING")
    object Game : FoxScreen(route = "GAME")
    object Bye : FoxScreen(route = "BYE")
}