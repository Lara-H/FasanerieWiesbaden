package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoxScreen() {


    var currentPoints by remember { mutableStateOf(0) }
    var moneyVisible by remember { mutableStateOf(1f) }

    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.title_location_goat), isMainNav = false) },
    ) {
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = "Streiche über die Erde, um zu graben")

        Image(
            painter = painterResource(
            id = R.drawable.beehome),
            contentDescription = "Money",
            modifier = Modifier
                .size(50.dp)
                .alpha(moneyVisible)
                .align(Alignment.CenterHorizontally)
        )

        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = 5,
            numberFull = currentPoints
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FoxScreenPreview() {
    FoxScreen()
}