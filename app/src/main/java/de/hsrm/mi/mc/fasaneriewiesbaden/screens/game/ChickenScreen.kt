@file:Suppress("UNCHECKED_CAST")

package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.sizing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.ChickenViewModel
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChickenScreen(onClose: () -> Unit, onDone: () -> Unit, screenSize: ScreenSize) {

    // helper to convert dp to px
    val density = LocalDensity.current

    // image size
    val chickenSize = 200.dp
    val chickenSizePx = with(density) {chickenSize.roundToPx()}
    val eggSize = 50.dp
    val eggSizePx = with(density) {eggSize.roundToPx()}
    val topBarPx = with(density) {MaterialTheme.sizing.topBar.roundToPx()}
    val progressBarPx = with(density) {MaterialTheme.sizing.progressBar.roundToPx()}

    // viewModel
    val viewModel = viewModel<ChickenViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ChickenViewModel(
                    screenSize = screenSize,
                    offsetTop = topBarPx * 2,
                    offsetBottom = progressBarPx,
                    eggSizePx = eggSizePx,
                    chickenSizePx = chickenSizePx
                ) as T
            }
        }
    )

    // check if done
    if (viewModel.isDone.value) {
        LaunchedEffect(Unit) {
            onDone()
        }
    }

    Box (modifier = Modifier .fillMaxSize() .background(MaterialTheme.colorScheme.background)) {
        // Brown chicken
        Image(
            painter = painterResource(id = R.drawable.chicken_brown),
            contentDescription = "Brown Chicken",
            modifier = Modifier
                .size(chickenSize)
                .offset { IntOffset(0, screenSize.screenHeightPx-chickenSizePx) }
        )
        // White chicken
        Image(
            painter = painterResource(id = R.drawable.chicken),
            contentDescription = "White chicken",
            modifier = Modifier
                .size(chickenSize)
                .offset { IntOffset(screenSize.screenWidthPx-chickenSizePx, screenSize.screenHeightPx-chickenSizePx) }
        )
        // Egg
        Image(
            painter = painterResource(id = viewModel.egg.imgPath),
            contentDescription = "Egg",
            modifier = Modifier
                .size(eggSize)
                .offset { IntOffset(viewModel.egg.offsetX.roundToInt(), viewModel.egg.offsetY.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount -> change.consume()
                        viewModel.changeEggPosition(dragAmount.x, dragAmount.y)
                }
            }
        )
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            TopBar(text = stringResource(R.string.title_location_chicken), onClose = onClose)
            TextBox(text = stringResource(R.string.station_chicken_game_text))
        }

        ProcessBar(
            icon = R.drawable.icon_chicken,
            numberTotal = viewModel.totalPoints,
            numberFull = viewModel.currentPoints
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChickenScreenPreview() {
    ChickenScreen(
        onClose = {},
        onDone = {},
        screenSize = ScreenSize(0.dp, 0.dp, 0, 0),
    )
}