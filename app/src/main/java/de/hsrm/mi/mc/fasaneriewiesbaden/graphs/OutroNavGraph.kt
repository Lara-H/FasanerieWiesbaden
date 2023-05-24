package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.CommunicationScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub.TextImageScreen

fun NavGraphBuilder.outroNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.OUTRO,
        startDestination = OutroScreen.Speech.route
    ) {
        composable(route = OutroScreen.Speech.route) {
            CommunicationScreen(
                title = "Elli Eichhorn",
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = "Lass uns alle unseren neuen Freunde holen, um gemeinsam den Schatz zu finden und den Park zu retten!",
                btnText = "Weiter",
                onBtnClick = { navController.navigate(OutroScreen.Treasure.route) }
            )
        }
        composable(route = OutroScreen.Treasure.route) {
            TextImageScreen(
                title = "Fledermaushöhle",
                imagePath = R.drawable.treasure,
                imageDescription= "Treasure",
                text = "Gemeinsam gelingt es den Tieren den Schatz zu bergen…",
                btnText = "Weiter",
                onBtnClick = { navController.navigate(OutroScreen.Thanks.route) }
            )
        }
        composable(route = OutroScreen.Thanks.route) {
            CommunicationScreen(
                title = "Elli Eichhorn",
                imagePath = R.drawable.squirrel,
                imageDescription= "Squirrel",
                text = "Hurra, der Park ist gerettet!\n" +
                        "\n" +
                        "Vielen Dank für deine Hilfe! Als kleines Dankeschön möchten wir dir natürlich auch etwas von unserem Fund abgeben. \n" +
                        "\n" +
                        "Mit diesem kannst du dir im Shop des Tierparks etwas Tolles aussuchen.",
                btnText = "Code einlösen",
                onBtnClick = { navController.navigate(OutroScreen.Code.route) }
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