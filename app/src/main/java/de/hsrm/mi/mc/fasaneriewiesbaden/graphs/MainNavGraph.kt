package de.hsrm.mi.mc.fasaneriewiesbaden.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.UiText
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.InfoScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.LevelScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.screens.main.MapScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainViewModel

@Composable
fun MainNavGraph(navController: NavHostController, mainNavController: NavHostController, data: MainActivityViewModel, viewModelMain: MainViewModel) {
    // title on first visit
    viewModelMain.updateTitle(MainScreen.Map.title.asString())

    NavHost(
        navController = mainNavController,
        route = Graph.MAIN,
        startDestination = MainScreen.Map.route
    ) {
        composable(
            route = MainScreen.Map.route
        ) {
            MapScreen(
                navController = navController,
                data = data,
            )
        }
        composable(
            route = MainScreen.Level.route
        ) {
            LevelScreen(
                data = data,
            )
        }
        composable(
            route = MainScreen.Info.route
        ) {
            InfoScreen()
        }
    }
}

sealed class MainScreen(val route: String, val icon: Int, val title: UiText) {
    object Map: MainScreen(route = "map_screen", icon = R.drawable.icon_map, title = UiText.StringResource(resId = R.string.title_map))
    object Level: MainScreen(route = "level_screen", icon = R.drawable.icon_level, title = UiText.StringResource(resId = R.string.title_level))
    object Info: MainScreen(route = "info_screen", icon = R.drawable.icon_info, title = UiText.StringResource(resId = R.string.title_info))
}