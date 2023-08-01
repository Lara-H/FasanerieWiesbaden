package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.MainScreen
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainViewModel

@Composable
fun BottomBar(navController: NavHostController, viewModelMain: MainViewModel) {
    val screens = listOf(
        MainScreen.Map,
        MainScreen.Level,
        MainScreen.Info,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        modifier = Modifier .height(70.dp),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = Color.White) {
        var i = 0
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                viewModelMain = viewModelMain
            )
            if (i < screens.size-1) {
                Box(modifier = Modifier
                    .size(1.dp, 50.dp)
                    .background(MaterialTheme.colorScheme.onSecondary)
                )
            }
            i++
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: MainScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    viewModelMain: MainViewModel,
) {
    val context = LocalContext.current
    NavigationBarItem(
        modifier = Modifier
            .size(30.dp),
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            viewModelMain.updateTitle(screen.title.asString(context))
            navController.navigate(screen.route)
        },
        colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
            indicatorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview
@Composable
fun BottomBarPreview() {
    val navController = rememberNavController()
    BottomBar(
        navController = navController,
        viewModelMain = MainViewModel(),
    )
}