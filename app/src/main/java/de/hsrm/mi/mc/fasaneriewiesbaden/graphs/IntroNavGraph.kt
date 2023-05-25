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
                text = "Hallo, mein Name ist Elli Eichhorn und ich bewache diesen Park.\n" +
                        "\n" +
                        "Aber der Park ist in Gefahr! Wenn wir nicht bald genug Geld auftreiben muss der Park verkauft werden und alle Tiere müssen sich ein neues Zuhause suchen.\n" +
                        "\n" +
                        "Jetzt kann uns nur noch der Goldschatz retten, der laut einer alten Legende im Park versteckt ist. Hilfst du uns ihn zu finden?",
                btnText = stringResource(R.string.intro_greeting_btn),
                onBtnClick = { navController.navigate(IntroScreen.Explanation.route) }
            )
        }
        composable(route = IntroScreen.Explanation.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_squirrel),
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = "Vielen Dank, dass du uns helfen möchtest! Du scheinst ein echter Tierfreund zu sein.\n" +
                        "\n" +
                        "Um den Schatz zu finden sollten wir die Tiere befragen, diese kennen sich hier am besten aus und zusammen können wir den Schatz bestimmt finden.\n" +
                        "\n" +
                        "Moment, was höre ich da für ein Geräusch…\n",
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(IntroScreen.Meeting.route) }
            )
        }
        composable(route = IntroScreen.Meeting.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_goat_child),
                imagePath = R.drawable.goat_child,
                imageDescription= "Goat child",
                text = "Määääh, mein Name ist Becky.\n" +
                        "\n" +
                        "Ich übe gerade meine neuen Tanzschritte, um Profitänzerin zu werden!",
                btnText = stringResource(R.string.communication_btn),
                onBtnClick = { navController.navigate(IntroScreen.Departure.route) }
            )
        }
        composable(route = IntroScreen.Departure.route) {
            CommunicationScreen(
                title = stringResource(R.string.title_name_squirrel),
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = "Hallo Becky, \n" +
                        "\n" +
                        "Deine Tanzschritte sehen schon richtig toll aus, ich bin mir sicher du wirst mal eine große Tänzerin!\n" +
                        "\n" +
                        "Deine Eltern machen sich aber bestimmt schon Sorgen. Komm, wir begleiten dich nach Hause, ich möchte sowieso noch mit deinem Papa reden.",
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