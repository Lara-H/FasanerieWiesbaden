package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.MainNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.MainTopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(data: Data) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { MainTopBar("LOREM IPSUM", isMainNav = true) },
        bottomBar = { BottomBar(navController = navController)}
    ) {
        MainNavGraph(navController = navController, data = data)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(data = Data())
}