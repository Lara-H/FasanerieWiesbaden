package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.OtterScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.otterNavGraph(navController: NavHostController, data: Data) {

    navigation(
        route = Graph.OTTER,
        startDestination = OtterScreen.Greeting.route
    ) {
        composable(route = OtterScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_otter),
                imagePath = R.drawable.otter,
                imageDescription= "Otter",
                text = stringResource(R.string.station_otter_greeting_text),
                btnText = stringResource(R.string.station_otter_greeting_btn),
                onBtnClick = { navController.navigate(OtterScreen.Game.route) }
            )
        }
        composable(route = OtterScreen.Game.route) {
            OtterScreen(
                onDone = { navController.navigate(OtterScreen.Bye.route) }
            )
        }
        composable(route = OtterScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_otter),
                imagePath = R.drawable.otter,
                imageDescription= "Otter",
                text = stringResource(R.string.station_otter_bye_text),
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

sealed class OtterScreen(val route: String) {
    object Greeting : OtterScreen(route = "OTTER_GREETING")
    object Game : OtterScreen(route = "OTTER_GAME")
    object Bye : OtterScreen(route = "OTTER_BYE")
}