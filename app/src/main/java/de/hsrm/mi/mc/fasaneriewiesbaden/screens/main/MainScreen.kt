package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.hsrm.mi.mc.fasaneriewiesbaden.LocationDetails
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.MainNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.MainTopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, data: MainActivityViewModel, currentLocation: LocationDetails) {
    val mainNavController = rememberNavController()
    Scaffold(
        topBar = { MainTopBar("LOREM IPSUM", isMainNav = true) },

    ) {
        Box(
            modifier = Modifier .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            MainNavGraph(navController = navController, mainNavController = mainNavController, data = data, currentLocation = currentLocation)
            BottomBar(navController = mainNavController)
        }

    }
}