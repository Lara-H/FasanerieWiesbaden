package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CodeScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.TextImageScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.outroNavGraph(navController: NavHostController, data: MainActivityViewModel) {
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
                onClose = { navController.navigate(Graph.MAIN) },
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
                onClose = { navController.navigate(Graph.MAIN) },
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
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(OutroScreen.Code.route) }
            )
        }
        composable(route = OutroScreen.Code.route) {
            CodeScreen(
                onClose = { navController.navigate(Graph.MAIN) },
                id = data.userID.toString()
            )
        }
    }
}

sealed class OutroScreen(val route: String) {
    object Speech : OutroScreen(route = "OUTRO_SPEECH")
    object Treasure : OutroScreen(route = "OUTRO_TREASURE")
    object Thanks : OutroScreen(route = "OUTRO_THANKS")
    object Code : OutroScreen(route = "OUTRO_CODE")
}