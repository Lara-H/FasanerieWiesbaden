package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.accessibility.AccessibilityManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoxScreen() {

    var currentPoints by remember { mutableStateOf(0) }
    var moneyVisible by remember { mutableStateOf(0f) }

    var oldrotation by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }

    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        rotation += rotationChange
    }

    if ((rotation - oldrotation > 45f)) {
        if (moneyVisible == 0f) {
            moneyVisible = 1f
            currentPoints++
        } else {
            moneyVisible = 0f
        }
        oldrotation = rotation
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF5b452d)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column() {
            TopBar(text = stringResource(R.string.title_location_fox), isMainNav = false)
            Text(text = "Rotiere den Bohrer mit zwei Fingern, um zu graben", Modifier.padding(all = MaterialTheme.spacing.medium), color = Color.White)
        }

        Box(
        ) {
            Image(
                painter = painterResource(id = R.drawable.hole),
                contentDescription = "Hole",
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Image(
                modifier = Modifier
                    // apply other transformations like rotation and zoom
                    // on the pizza slice emoji
                    .padding(top = 60.dp, bottom = 40.dp)
                    .graphicsLayer(
                        rotationZ = rotation,
                    )
                    // add transformable to listen to multitouch transformation events
                    // after offset
                    .transformable(state = state)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.drill),
                contentDescription = "Hole",
            )
            Image(
                painter = painterResource(id = R.drawable.beehome),
                contentDescription = "Money",
                modifier = Modifier
                    .size(50.dp)
                    .alpha(moneyVisible)
                    .align(Alignment.TopCenter)
            )
        }

        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = 5,
            numberFull = currentPoints
        )
    }

}

fun Fragment.vibratePhone() {
    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(200)
    }
}

private fun Context.isTouchExplorationEnabled(): Boolean {
    // can be null during unit tests
    val accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?
    return accessibilityManager?.isTouchExplorationEnabled ?: false
}

@Preview(showBackground = true)
@Composable
fun FoxScreenPreview() {
    FoxScreen()
}