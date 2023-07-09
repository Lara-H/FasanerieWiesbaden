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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
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
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChickenScreen() {

    // helpers to convert dp to px
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    // device size
    val screenHeightPx = with(density) {configuration.screenHeightDp.dp.roundToPx()}
    val screenWidthPx = with(density) {configuration.screenWidthDp.dp.roundToPx()}

    // image size
    val chickenSize = 200.dp
    val chickenSizePx = with(density) {chickenSize.roundToPx()}
    val eggSize = 50.dp
    val eggSizePx = with(density) {eggSize.roundToPx()}

    val viewModel = viewModel<ChickenViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ChickenViewModel(
                    screenHeightPx = screenHeightPx,
                    screenWidthPx = screenWidthPx,
                    offsetTop = 400,
                    offsetBottom = 300,
                    eggSizePx = eggSizePx,
                    chickenSizePx = chickenSizePx
                ) as T
            }
        }
    )

    Box (modifier = Modifier .fillMaxSize() .background(MaterialTheme.colorScheme.background)) {

            Image(
                painter = painterResource(
                    id = R.drawable.chicken_brown),
                contentDescription = "Chicken",
                modifier = Modifier
                    .size(chickenSize)
                    .offset { IntOffset(0, screenHeightPx-chickenSizePx) }
            )

            Image(
                painter = painterResource(
                    id = R.drawable.chicken),
                contentDescription = "Chicken",
                modifier = Modifier
                    .size(chickenSize)
                    .offset { IntOffset(screenWidthPx-chickenSizePx, screenHeightPx-chickenSizePx) }
            )

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
            TopBar(text = stringResource(R.string.title_location_chicken), isMainNav = false)
            Text(text = "Sammel die Eier ein und übergebe sie dem gleichfarbigen Huhn", modifier = Modifier .padding(all = MaterialTheme.spacing.medium))
        }

        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = 5,
            numberFull = viewModel.currentPoints
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChickenScreenPreview() {
    ChickenScreen()
}