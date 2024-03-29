@file:Suppress("UNCHECKED_CAST")

package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.colorpalette
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.BatViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BatScreen(onClose: () -> Unit, onDone: () -> Unit, screenSize: ScreenSize) {
    val doorWidth = screenSize.screenWidth - (MaterialTheme.spacing.medium * 2)

    // viewModel
    val viewModel = viewModel<BatViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BatViewModel(
                    doorWidth = doorWidth,
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

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorpalette.gray_700),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            TopBar(text = stringResource(R.string.title_location_bat), onClose = onClose)
            TextBox(text = stringResource(R.string.station_bat_game_text), colorText = Color.White)
        }

        Box (modifier = Modifier
            .padding(all = MaterialTheme.spacing.medium)
            .fillMaxSize()
            .background(Color.Black)
        ) {

            // door open animations
            val interpolated = FastOutLinearInEasing.transform(0.25f)
            val deg = 105f
            val distort: Float by animateFloatAsState(if (viewModel.visible.value) 0f else kotlin.math.min(interpolated * deg, 90f))

            Box(modifier = Modifier
                .border(width = 5.dp, color = MaterialTheme.colorpalette.gray_600)
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = distort
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0f,
                        pivotFractionY = 0.5f
                    )
                }) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painterResource(id = R.drawable.bat_door),
                            contentScale = ContentScale.FillBounds
                        )
                        .border(width = 1.dp, color = MaterialTheme.colorpalette.gray_600)
                ) {
                    // Drills
                    viewModel.drills.forEach {
                        Box(modifier = Modifier .offset(it.offsetX, it.offsetY)) {
                            val alpha: Float by animateFloatAsState(if (viewModel.visible.value) 0.75f else 0.5f)
                            Image(
                                modifier = Modifier
                                    .graphicsLayer(
                                        rotationZ = it.rotation,
                                    )
                                    .transformable(state = rememberTransformableState { _, _, rotationChange ->
                                        viewModel.rotate(it.id, rotationChange)
                                    })
                                    .size(it.size),
                                painter = painterResource(id = it.imgPath),
                                contentDescription = "Drill",
                                alpha = alpha
                            )
                        }
                    }

                    Box(modifier = Modifier
                        .offset(viewModel.doorWidth - 50.dp - viewModel.paddingBorder, viewModel.drillWidth + viewModel.drillWidth/2)
                    ) {
                        Box(modifier = Modifier
                            .background(MaterialTheme.colorpalette.gray_600)
                            .width(50.dp)
                            .height(20.dp)
                        )
                    }

                }

            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun BatScreenPreview() {
    BatScreen(
        onClose = {},
        onDone = {},
        screenSize = ScreenSize(0.dp, 0.dp, 0, 0),
    )
}