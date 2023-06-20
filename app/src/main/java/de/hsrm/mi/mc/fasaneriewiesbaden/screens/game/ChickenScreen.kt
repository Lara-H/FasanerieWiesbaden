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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChickenScreen() {
    var currentPoints by remember { mutableStateOf(0) }
    var imgPath by remember { mutableStateOf(R.drawable.egg) }

    // helpers to convert dp to px
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    // device size
    val screenHeightPx = with(density) {configuration.screenHeightDp.dp.roundToPx()}
    val screenWidthPx = with(density) {configuration.screenWidthDp.dp.roundToPx()}

    // offset header and footer
    val offsetTop = 200
    val offsetBottom = 300

    // image size
    val chickenSize = 200.dp
    val chickenSizePx = with(density) {chickenSize.roundToPx()}
    val eggSize = 50.dp
    val eggSizePx = with(density) {eggSize.roundToPx()}

    // random
    val randomOffsetX = (0 until screenWidthPx-eggSizePx).shuffled().last().toFloat()
    val randomOffsetY = (with(density) {offsetTop.dp.roundToPx()} until screenHeightPx-offsetBottom-chickenSizePx).shuffled().last().toFloat()

    Box (modifier = Modifier .fillMaxSize() .background(MaterialTheme.colorScheme.background)) {
            var offsetX by remember { mutableStateOf(randomOffsetX) }
            var offsetY by remember { mutableStateOf(randomOffsetY) }

            // Start
            if (offsetX < 1f) {
                offsetX = 0f
            }
            // End
            if (offsetX > (screenWidthPx-eggSizePx).toFloat()) {
                offsetX = (screenWidthPx-eggSizePx).toFloat()
            }
            // Top
            if (offsetY <= offsetTop) {
                offsetY = offsetTop.toFloat()
            }
            // Bottom
            if (offsetY > (screenHeightPx-offsetBottom).toFloat()) {
                offsetY = (screenHeightPx-offsetBottom).toFloat()
            }

            // onLeftChicken
            if (offsetX < (screenWidthPx/2) && offsetY > screenHeightPx-chickenSizePx) {
                if (imgPath == R.drawable.egg_brown) {
                    currentPoints++
                }
                val random = (0..1).shuffled().last()
                imgPath = if (random == 0) {
                    R.drawable.egg
                } else {
                    R.drawable.egg_brown
                }
                offsetX = randomOffsetX
                offsetY = randomOffsetY
            }
            // onRightChicken
            if (offsetX > (screenWidthPx/2) && offsetY > screenHeightPx-chickenSizePx) {
                if (imgPath == R.drawable.egg) {
                    currentPoints++
                }
                val random = (0..1).shuffled().last()
                imgPath = if (random == 0) {
                    R.drawable.egg
                } else {
                    R.drawable.egg_brown
                }
                offsetX = randomOffsetX
                offsetY = randomOffsetY
            }

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
                painter = painterResource(id = imgPath),
                contentDescription = "Egg",
                modifier = Modifier
                    .size(eggSize)
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount -> change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
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
            numberFull = currentPoints
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChickenScreenPreview() {
    ChickenScreen()
}