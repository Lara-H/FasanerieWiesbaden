package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GamingScreen(title: String) {
    Scaffold(
        topBar = { TopBar(text=title, isMainNav = false) },
    ) {
    }
}

@Preview
@Composable
fun GamingScreenPreview() {
    GamingScreen(
        title = "Ziegenwiese",
    )
}