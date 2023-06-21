package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.lynxNavGraph(navController: NavHostController, data: Data) {

    navigation(
        route = Graph.LYNX,
        startDestination = LynxScreen.Greeting.route
    ) {
        composable(route = LynxScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_lynx),
                imagePath = R.drawable.lynx,
                imageDescription= "Lynx",
                text = stringResource(R.string.station_lynx_greeting_text),
                btnText = stringResource(R.string.station_lynx_greeting_btn),
                onBtnClick = { }
            )
        }
        composable(route = LynxScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_lynx),
                imagePath = R.drawable.lynx,
                imageDescription= "Lynx",
                text = stringResource(R.string.station_lynx_bye_text),
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

sealed class LynxScreen(val route: String) {
    object Greeting : LynxScreen(route = "GREETING")
    object Bye : LynxScreen(route = "BYE")
}