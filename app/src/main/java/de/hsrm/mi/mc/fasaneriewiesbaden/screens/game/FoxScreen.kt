package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoxScreen() {

    // helpers to convert dp to px
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    // device size
    val screenWidthPx = with(density) {configuration.screenWidthDp.dp.roundToPx()}

    // image size
    val imgSize = 100.dp
    val imgSizePx = with(density) {imgSize.roundToPx()}

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    var oldroffsetX by remember { mutableStateOf(0f) }
    var distance by remember { mutableStateOf(0f) }

    var currentPoints by remember { mutableStateOf(0) }
    var moneyVisible by remember { mutableStateOf(0f) }

    // Start
    if (offsetX < ((screenWidthPx/2)-(imgSizePx/2))*(-1)) {
        offsetX = ((screenWidthPx/2)-(imgSizePx/2))*(-1).toFloat()
    }
    // End
    if (offsetX > (screenWidthPx/2)-(imgSizePx/2)) {
        offsetX = (screenWidthPx/2)-(imgSizePx/2).toFloat()
    }
    // Top
    if (offsetY < imgSizePx*(-1)) {
        offsetY = imgSizePx*(-1).toFloat()
    }
    // Bottom
    if (offsetY > imgSizePx) {
        offsetY = imgSizePx.toFloat()
    }

    distance = oldroffsetX - offsetX
    if (distance < 0) {
        distance *= (-1)
    }

    if (distance > imgSizePx) {
        if (moneyVisible == 0f) {
            val random = (0..5).shuffled().last()
            if (random == 0) {
                moneyVisible = 1f
                currentPoints++
                Timer().schedule(5000){
                    moneyVisible = 0f
                }
            }
        }
        oldroffsetX = offsetX
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF5b452d)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column() {
            TopBar(text = stringResource(R.string.title_location_fox), isMainNav = false)
            Text(text = "Bewege die Hände mit deinem Finger über das Erdloch, um zu graben", Modifier.padding(all = MaterialTheme.spacing.medium), color = Color.White)
        }

        Box(modifier = Modifier .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.hole),
                contentDescription = "Hole",
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Image(
                painter = painterResource(id = R.drawable.money),
                contentDescription = "Money",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(30.dp)
                    .alpha(moneyVisible)
                    .offset { IntOffset((imgSizePx*(-1) until imgSizePx).shuffled().last(), (imgSizePx*(-1) until imgSizePx).shuffled().last()) }
            )
            Image(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
                    .size(100.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.hands),
                contentDescription = "Hands",
            )
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
fun FoxScreenPreview() {
    FoxScreen()
}