package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
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
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.FoxViewModel
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoxScreen(onClose: () -> Unit, onDone: () -> Unit) {

    // helpers to convert dp to px
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    // device size
    val screenWidthPx = with(density) {configuration.screenWidthDp.dp.roundToPx()}

    // image size
    val imgSize = 100.dp
    val imgSizePx = with(density) {imgSize.roundToPx()}

    // viewmodel
    val viewModel = viewModel<FoxViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FoxViewModel(
                    screenWidthPx = screenWidthPx,
                    imgSizePx = imgSizePx
                ) as T
            }
        }
    )

    // check if done
    if (viewModel.isDone.value) {
        onDone()
    }

    // haptic feedback
    val haptic = LocalHapticFeedback.current

    Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.fox_ground),
            contentScale = ContentScale.FillBounds
        ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column() {
            TopBar(text = stringResource(R.string.title_location_fox), onClose = onClose)
            Text(text = stringResource(R.string.station_fox_game_text), Modifier.padding(all = MaterialTheme.spacing.medium), color = Color.White)
        }

        Box(modifier = Modifier .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = viewModel.holeImgPath),
                contentDescription = viewModel.holeImgAltText,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(viewModel.holeVisible.value)
            )
            Image(
                painter = painterResource(id = viewModel.dustImgPath),
                contentDescription = viewModel.dustImgAltText,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(viewModel.dustVisible.value)
            )
            Image(
                painter = painterResource(viewModel.itemImgPath),
                contentDescription = viewModel.itemImgAltText,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(viewModel.itemImgSize)
                    .alpha(viewModel.isItemVisible.value)
                    .offset { IntOffset((imgSizePx*(-1) until imgSizePx).shuffled().last(), (imgSizePx*(-1) until imgSizePx).shuffled().last()) }
            )
            Image(
                modifier = Modifier
                    .offset { IntOffset(viewModel.hands.value.offsetX.roundToInt(), viewModel.hands.value.offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount -> change.consume()
                            viewModel.changeHandsPosition(dragAmount.x, dragAmount.y)
                        }
                    }
                    .size(viewModel.handsImgSize)
                    .align(Alignment.Center),
                painter = painterResource(id = viewModel.handsImgPath),
                contentDescription = viewModel.handsImgAltText,
            )
        }

        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = viewModel.totalPoints,
            numberFull = viewModel.currentPoints
        )
    }

}

@Preview(showBackground = true)
@Composable
fun FoxScreenPreview() {
    FoxScreen(
        onClose = {},
        onDone = {}
    )
}