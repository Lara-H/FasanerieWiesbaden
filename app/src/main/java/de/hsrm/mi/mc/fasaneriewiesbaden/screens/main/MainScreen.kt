package de.hsrm.mi.mc.fasaneriewiesbaden

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar("Hello World", isMainNav = true) },
        bottomBar = { BottomBar(navController = navController)}
    ) {
        MainNavGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

