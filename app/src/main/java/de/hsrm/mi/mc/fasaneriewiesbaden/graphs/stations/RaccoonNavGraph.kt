package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.RaccoonScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.raccoonNavGraph(navController: NavHostController, data: Data) {

    navigation(
        route = Graph.RACCOON,
        startDestination = RaccoonScreen.Greeting.route
    ) {
        composable(route = RaccoonScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_raccoon),
                imagePath = R.drawable.raccoon,
                imageDescription= "Raccoon",
                text = stringResource(R.string.station_raccoon_greeting_text),
                btnText = stringResource(R.string.station_raccoon_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(RaccoonScreen.Game.route) }
            )
        }
        composable(route = RaccoonScreen.Game.route) {
            RaccoonScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                onDone = { navController.navigate(RaccoonScreen.Bye.route) }
            )
        }
        composable(route = RaccoonScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_raccoon),
                imagePath = R.drawable.raccoon,
                imageDescription= "Raccoon",
                text = stringResource(R.string.station_raccoon_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN) },
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

sealed class RaccoonScreen(val route: String) {
    object Greeting : RaccoonScreen(route = "RACCOON_GREETING")
    object Game : RaccoonScreen(route = "RACCOON_GAME")
    object Bye : RaccoonScreen(route = "RACCOON_BYE")
}