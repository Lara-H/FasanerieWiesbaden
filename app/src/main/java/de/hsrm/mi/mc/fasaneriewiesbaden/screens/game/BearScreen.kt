@file:Suppress("UNCHECKED_CAST")

package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
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
import de.hsrm.mi.mc.fasaneriewiesbaden.components.PrimaryButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.sizing
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.BearViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType",
    "MutableCollectionMutableState"
)
@Composable
fun BearScreen(onClose: () -> Unit, onDone: () -> Unit, screenSize: ScreenSize) {

    // helper to convert dp to px
    val density = LocalDensity.current

    // image size
    val itemSize = 50.dp
    val cupSize = 100.dp
    val topBarPx = with(density) {MaterialTheme.sizing.topBar.roundToPx()}

    // viewModel
    val viewModel = viewModel<BearViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BearViewModel(
                    screenSize = screenSize,
                    gameBoxHeightPx = screenSize.screenHeightPx - topBarPx - with(density) {itemSize.roundToPx()},
                    cupSizePx = with(density) {cupSize.roundToPx()},
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

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    // animation
    val initialValue = 40f
    var targetValue = 40f

    if (viewModel.startGame.value) {
        targetValue = -40f
    }
    val transition = rememberInfiniteTransition()
    val rotate: Float by transition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = EaseInOutCirc),
            repeatMode = RepeatMode.Reverse
        )
    )

    // move Items
    if (viewModel.startGame.value) {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel.moveItems()
                viewModel.checkIfCaught()
                mainHandler.postDelayed(this, 1000)
                mainHandler.removeCallbacks(this)
            }
        })
    }

    Box(modifier = Modifier .fillMaxSize()) {
        // Honey Drop
        Image(
            painter = painterResource(
                id = R.drawable.beehoney),
            contentDescription = "Honey",
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.extraLarge)
                .offset { IntOffset(viewModel.drop.value.offsetX, viewModel.drop.value.offsetY) }
                .size(itemSize)
        )
        // Bee
        Image(
            painter = painterResource(
                id = R.drawable.bear_bee),
            contentDescription = "Bee",
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.extraLarge)
                .offset { IntOffset(viewModel.bee.value.offsetX, viewModel.bee.value.offsetY) }
                .size(itemSize)
        )
    }

    Column(modifier = Modifier .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(modifier = Modifier) {

            Column {
                TopBar(text = stringResource(R.string.title_location_bear), onClose = onClose)
                TextBox(stringResource(R.string.station_bear_game_text), Color.White, MaterialTheme.colorScheme.onBackground)
                Box {
                    // Tree
                    Image(
                        painter = painterResource(
                            id = R.drawable.beetree),
                        contentDescription = "Tree",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                    )
                    // Bee home
                    Image(
                        painter = painterResource(
                            id = R.drawable.beehome),
                        contentDescription = "Bee Home",
                        modifier = Modifier
                            .padding(
                                start = MaterialTheme.spacing.extraLarge,
                                end = MaterialTheme.spacing.extraLarge
                            )
                            .rotate(rotate)
                    )

                }
            }
        }

        Column {
            // Start Button
            if (!viewModel.startGame.value) {
                Column(
                    modifier = Modifier .fillMaxWidth() .padding(bottom = MaterialTheme.spacing.large),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    PrimaryButton(text = stringResource(R.string.station_bear_game_btn), onClick = { viewModel.startGame() })
                }
            }
            // Cup
            Image(
                painter = painterResource(id = R.drawable.bear_cup),
                contentDescription = "Cup",
                modifier = Modifier
                    .size(cupSize)
                    .offset { IntOffset(viewModel.cupPosition.value, 0) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            viewModel.changeCupPosition(dragAmount.x)
                        }
                    }
            )

            ProcessBar(
                icon = R.drawable.icon_bear,
                numberTotal = viewModel.totalPoints,
                numberFull = viewModel.currentPoints
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun BearScreenPreview() {
    BearScreen(
        onClose = {},
        onDone = {},
        screenSize = ScreenSize(0.dp, 0.dp, 0, 0)
    )
}