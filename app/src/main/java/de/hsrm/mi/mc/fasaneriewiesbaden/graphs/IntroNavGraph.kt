package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

fun NavGraphBuilder.introNavGraph(navController: NavHostController, data: MainActivityViewModel) {

    navigation(
        route = Graph.INTRO,
        startDestination = IntroScreen.Greeting.route
    ) {
        composable(route = IntroScreen.Greeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_squirrel),
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = stringResource(R.string.intro_greeting_text),
                btnText = stringResource(R.string.intro_greeting_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(IntroScreen.Explanation.route) }
            )
        }
        composable(route = IntroScreen.Explanation.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_squirrel),
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = stringResource(R.string.intro_explanation_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(IntroScreen.Meeting.route) }
            )
        }
        composable(route = IntroScreen.Meeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat_child),
                imagePath = R.drawable.goat_child,
                imageDescription= "Goat child",
                text = stringResource(R.string.intro_meeting_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN) },
                onBtnClick = { navController.navigate(IntroScreen.Departure.route) }
            )
        }
        composable(route = IntroScreen.Departure.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_squirrel),
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = stringResource(R.string.intro_departure_text),
                btnText = stringResource(R.string.communication_btn),
                onClose = { navController.navigate(Graph.MAIN); data.stationDone() },
                onBtnClick = { navController.navigate(Graph.MAIN); data.stationDone() },
            )
        }
    }
}

sealed class IntroScreen(val route: String) {
    object Greeting : IntroScreen(route = "INTRO_GREETING")
    object Explanation : IntroScreen(route = "INTRO_EXPLANATION")
    object Meeting : IntroScreen(route = "INTRO_MEETING")
    object Departure : IntroScreen(route = "INTRO_DEPARTURE")
}