package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.PrimaryButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.BearViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType",
    "MutableCollectionMutableState"
)
@Composable
fun BearScreen(onClose: () -> Unit, onDone: () -> Unit) {

    // device size
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val dropSize = 50.dp
    val cupSize = 100.dp
    val cupSizePx = with(density) {cupSize.roundToPx()}
    val screenHeight = configuration.screenHeightDp.dp - dropSize - 70.dp
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeightPx = with(density) {screenHeight.roundToPx()}
    val screenWidthPx = with(density) {screenWidth.roundToPx()}


    val viewModel = viewModel<BearViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BearViewModel(
                    screenHeight = screenHeight,
                    screenWidth = screenWidth,
                    screenHeightPx = screenHeightPx,
                    screenWidthPx = screenWidthPx,
                    cupSizePx = cupSizePx
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
    var initialValue = 40f
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

    // drop
    if (viewModel.startGame.value) {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel.drop()
                viewModel.checkIfCatched()
                mainHandler.postDelayed(this, 1000)
                mainHandler.removeCallbacks(this)
            }
        })
    }

    Box(modifier = Modifier .fillMaxSize()) {
        Image(
            painter = painterResource(
                id = R.drawable.beehoney),
            contentDescription = "Honey",
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.extraLarge)
                .offset { IntOffset(viewModel.drop.value.offsetX, viewModel.drop.value.offsetY) }
                .size(dropSize)
        )
        Image(
            painter = painterResource(
                id = R.drawable.fox_bug),
            contentDescription = "Bee",
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.extraLarge)
                .offset { IntOffset(viewModel.bee.value.offsetX, viewModel.bee.value.offsetY) }
                .size(dropSize)
        )
    }

    Column(modifier = Modifier .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(modifier = Modifier) {

            Column {
                TopBar(text = stringResource(R.string.title_location_bear), onClose = onClose)
                TextBox(stringResource(R.string.station_bear_game_text), Color.White, MaterialTheme.colorScheme.onBackground)
                Box {

                    Image(
                        painter = painterResource(
                            id = R.drawable.beetree),
                        contentDescription = "Tree",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                    )

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
            if (!viewModel.startGame.value) {
                Column(
                    modifier = Modifier .fillMaxWidth() .padding(bottom = MaterialTheme.spacing.large),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    PrimaryButton(text = "Starten", onClick = { viewModel.startGame() })
                }
            }
            Image(
                painter = painterResource(id = R.drawable.fish),
                contentDescription = "Item",
                modifier = Modifier
                    .size(100.dp)
                    .offset { IntOffset(viewModel.cupPosition.value.roundToInt(), 0) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            viewModel.changeCupPosition(dragAmount.x, dragAmount.y)
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

// https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/package-summary#(androidx.compose.ui.Modifier).onPlaced(kotlin.Function1)
fun Modifier.animatePlacement(): Modifier = composed {
    val scope = rememberCoroutineScope()
    var targetOffset by remember { mutableStateOf(IntOffset.Zero) }
    var animatable by remember {
        mutableStateOf<Animatable<IntOffset, AnimationVector2D>?>(null)
    }
    this
        .onPlaced {
            targetOffset = it
                .positionInParent()
                .round()
        }
        .offset {
            val anim = animatable ?: Animatable(targetOffset, IntOffset.VectorConverter)
                .also {
                    animatable = it
                }
            if (anim.targetValue != targetOffset) {
                scope.launch {
                    anim.animateTo(targetOffset, spring(stiffness = Spring.StiffnessMediumLow))
                }
            }
            animatable?.let { it.value - targetOffset } ?: IntOffset.Zero
        }
}

@Preview(showBackground = true)
@Composable
fun BearScreenPreview() {
    BearScreen(
        onClose = {},
        onDone = {}
    )
}