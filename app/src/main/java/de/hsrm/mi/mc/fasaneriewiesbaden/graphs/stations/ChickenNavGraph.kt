package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.ChickenScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.chickenNavGraph(navController: NavHostController, data: Data) {

    navigation(
        route = Graph.CHICKEN,
        startDestination = ChickenScreen.Game.route
    ) {
        composable(route = ChickenScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_chicken),
                imagePath = R.drawable.chicken,
                imageDescription= "chicken",
                text = stringResource(R.string.station_chicken_greeting_text),
                btnText = stringResource(R.string.station_chicken_greeting_btn),
                onBtnClick = { navController.navigate(ChickenScreen.Game.route) }
            )
        }
        composable(route = ChickenScreen.Game.route) {
            ChickenScreen()
        }
        composable(route = ChickenScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_chicken),
                imagePath = R.drawable.chicken,
                imageDescription= "chicken",
                text = stringResource(R.string.station_chicken_bye_text),
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

sealed class ChickenScreen(val route: String) {
    object Greeting : ChickenScreen(route = "CHICKEN_GREETING")
    object Game : ChickenScreen(route = "CHICKEN_GAME")
    object Bye : ChickenScreen(route = "CHICKEN_BYE")
}