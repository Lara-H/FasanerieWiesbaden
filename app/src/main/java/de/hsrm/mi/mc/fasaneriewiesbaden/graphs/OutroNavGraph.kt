package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CodeScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.TextImageScreen

fun NavGraphBuilder.outroNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.OUTRO,
        startDestination = OutroScreen.Speech.route
    ) {
        composable(route = OutroScreen.Speech.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_squirrel),
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = stringResource(R.string.outro_speech_text),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(OutroScreen.Treasure.route) }
            )
        }
        composable(route = OutroScreen.Treasure.route) {
            TextImageScreen(
                title = stringResource(R.string.title_location_bat),
                imagePath = R.drawable.treasure,
                imageDescription= "Treasure",
                text = stringResource(R.string.outro_treasure_text),
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(OutroScreen.Thanks.route) }
            )
        }
        composable(route = OutroScreen.Thanks.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_squirrel),
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = stringResource(R.string.outro_thanks_text),
                btnText = stringResource(R.string.outro_code_btn),
                onBtnClick = { navController.navigate(OutroScreen.Code.route) }
            )
        }
        composable(route = OutroScreen.Code.route) {
            CodeScreen(
                url = "https://fasanerie.net/"
            )
        }
    }
}

sealed class OutroScreen(val route: String) {
    object Speech : OutroScreen(route = "SPEECH")
    object Treasure : OutroScreen(route = "TREASURE")
    object Thanks : OutroScreen(route = "THANKS")
    object Code : OutroScreen(route = "CODE")
}