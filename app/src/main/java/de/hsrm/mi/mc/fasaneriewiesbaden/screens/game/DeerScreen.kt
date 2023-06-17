package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnsafeOptInUsageError")
@Composable
fun DeerScreen() {


    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.title_location_deer), isMainNav = false) },
    ) {
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = "Halte den QR Code an den Scanner am Futterautomat")


    }
}

@Preview(showBackground = true)
@Composable
fun DeerScreenPreview() {
    DeerScreen()
}