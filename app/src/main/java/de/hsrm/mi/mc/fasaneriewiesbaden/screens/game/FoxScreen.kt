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
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
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
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.FoxViewModel
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoxScreen(onClose: () -> Unit, onDone: () -> Unit, screenSize: ScreenSize) {

    // helper to convert dp to px
    val density = LocalDensity.current

    // image size
    val handsImgSize = 100.dp
    val handsImgSizePx = with(density) {handsImgSize.roundToPx()}

    // viewModel
    val viewModel = viewModel<FoxViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FoxViewModel(
                    screenSize = screenSize,
                    handsImgSizePx = handsImgSizePx
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

    Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.fox_ground),
            contentScale = ContentScale.FillBounds
        ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            TopBar(text = stringResource(R.string.title_location_fox), onClose = onClose)
            TextBox(text = stringResource(R.string.station_fox_game_text), colorText = Color.White)
        }

        Box(modifier = Modifier .fillMaxWidth()
        ) {
            // Hole
            Image(
                painter = painterResource(id = R.drawable.fox_hole),
                contentDescription = "Hole",
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(viewModel.holeVisible.value)
            )
            // Dust
            Image(
                painter = painterResource(id = R.drawable.fox_dust),
                contentDescription = "Dust",
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(viewModel.dustVisible.value)
            )
            // Bug
            Image(
                painter = painterResource(R.drawable.fox_bug),
                contentDescription = "Bug",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp)
                    .alpha(viewModel.bugVisible.value)
                    .offset { IntOffset((handsImgSizePx*(-1) until handsImgSizePx).random(), (handsImgSizePx*(-1) until handsImgSizePx).random()) }
            )
            // Hands
            Image(
                modifier = Modifier
                    .offset { IntOffset(viewModel.hands.value.offsetX.roundToInt(), viewModel.hands.value.offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount -> change.consume()
                            viewModel.changeHandsPosition(dragAmount.x, dragAmount.y)
                        }
                    }
                    .size(handsImgSize)
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.hands),
                contentDescription = "Hands",
            )
        }

        ProcessBar(
            icon = R.drawable.icon_fox,
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
        onDone = {},
        screenSize = ScreenSize(0.dp, 0.dp, 0, 0),
    )
}