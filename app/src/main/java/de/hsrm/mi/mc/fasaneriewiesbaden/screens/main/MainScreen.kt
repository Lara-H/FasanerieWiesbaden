package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.MainNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.MainTopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, data: MainActivityViewModel) {
    val mainNavController = rememberNavController()

    // viewmodel
    val viewModel = viewModel<MainViewModel>()

    Scaffold(
        topBar = { MainTopBar(viewModel = viewModel, data = data, navController = navController) },

    ) {
        Box(
            modifier = Modifier .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            MainNavGraph(navController = navController, mainNavController = mainNavController, data = data, viewModelMain = viewModel)
            BottomBar(navController = mainNavController, viewModelMain = viewModel)
        }
    }

}
