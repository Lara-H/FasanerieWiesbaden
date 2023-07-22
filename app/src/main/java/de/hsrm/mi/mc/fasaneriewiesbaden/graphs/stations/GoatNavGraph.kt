package de.hsrm.mi.mc.fasaneriewiesbaden.graphs.stations

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.game.GoatScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.goatNavGraph(navController: NavHostController, data: Data) {
    navigation(
        route = Graph.GOAT,
        startDestination = GoatScreen.Greeting.route
    ) {
        composable(route = GoatScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat),
                imagePath = R.drawable.goat,
                imageDescription= "Goat",
                text = stringResource(R.string.station_goat_greeting_text),
                btnText = stringResource(R.string.station_goat_greeting_btn),
                onBtnClick = { navController.navigate(GoatScreen.Game1.route) }
            )
        }
        composable(route = GoatScreen.Game1.route) {
            GoatScreen(
                isCorrect = true,
                imagePath = R.drawable.goat_1,
                currentPoints = 0,
                onCorrectClick = { navController.navigate(GoatScreen.Game2.route) },
                onFalseClick = { navController.navigate(GoatScreen.Error.route) }
            )
        }
        composable(route = GoatScreen.Game2.route) {
            GoatScreen(
                isCorrect = false,
                imagePath = R.drawable.goat_2,
                currentPoints = 1,
                onCorrectClick = { navController.navigate(GoatScreen.Game3.route) },
                onFalseClick = { navController.navigate(GoatScreen.Error.route) }
            )
        }
        composable(route = GoatScreen.Game3.route) {
            GoatScreen(
                isCorrect = true,
                imagePath = R.drawable.goat_3,
                currentPoints = 2,
                onCorrectClick = { navController.navigate(GoatScreen.Game4.route) },
                onFalseClick = { navController.navigate(GoatScreen.Error.route) }
            )
        }
        composable(route = GoatScreen.Game4.route) {
            GoatScreen(
                isCorrect = true,
                imagePath = R.drawable.goat_4,
                currentPoints = 3,
                onCorrectClick = { navController.navigate(GoatScreen.Game5.route) },
                onFalseClick = { navController.navigate(GoatScreen.Error.route) }
            )
        }
        composable(route = GoatScreen.Game5.route) {
            GoatScreen(
                isCorrect = false,
                imagePath = R.drawable.goat_5,
                currentPoints = 4,
                onCorrectClick = { navController.navigate(GoatScreen.Bye.route) },
                onFalseClick = { navController.navigate(GoatScreen.Error.route) }
            )
        }
        composable(route = GoatScreen.Bye.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat),
                imagePath = R.drawable.goat,
                imageDescription= "Goat",
                text = stringResource(R.string.station_goat_bye_text),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(Graph.MAIN) {
                    data.listStationsState.value[data.currentNumberState.value].isDone = true
                    data.currentNumberState.value++
                    data.currentStationState.value = data.listStationsState.value[data.currentNumberState.value]
                    }
                }
            )
        }
        composable(route = GoatScreen.Error.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat),
                imagePath = R.drawable.goat,
                imageDescription= "Goat",
                text = stringResource(R.string.station_goat_game_error),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(GoatScreen.Game1.route) }
            )
        }
    }
}

sealed class GoatScreen(val route: String) {
    object Greeting : GoatScreen(route = "GOAT_GREETING")
    object Game1 : GoatScreen(route = "GAME1")
    object Game2 : GoatScreen(route = "GAME2")
    object Game3 : GoatScreen(route = "GAME3")
    object Game4 : GoatScreen(route = "GAME4")
    object Game5 : GoatScreen(route = "GAME5")
    object Bye : GoatScreen(route = "GOAT_BYE")
    object Error : GoatScreen(route = "ERROR")
}