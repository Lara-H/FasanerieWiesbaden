package de.hsrm.mi.mc.fasaneriewiesbaden

sealed class Screen(val route: String) {
    object Map: Screen(route = "map_screen")
    object Level: Screen(route = "level_screen")
    object Info: Screen(route = "info_screen")
}
