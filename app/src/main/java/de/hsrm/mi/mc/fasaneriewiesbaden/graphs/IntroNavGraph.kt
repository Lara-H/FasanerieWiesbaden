package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen

fun NavGraphBuilder.introNavGraph(navController: NavHostController) {
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
                onBtnClick = { navController.navigate(Graph.MAIN) },
            )
        }
    }
}

sealed class IntroScreen(val route: String) {
    object Greeting : IntroScreen(route = "GREETING")
    object Explanation : IntroScreen(route = "EXPLANATION")
    object Meeting : IntroScreen(route = "MEETING")
    object Departure : IntroScreen(route = "DEPARTURE")
}