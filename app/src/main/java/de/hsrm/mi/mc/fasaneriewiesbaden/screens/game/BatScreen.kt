package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BatScreen() {

    var rotation2 by remember { mutableStateOf(123f) }
    val state2 = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        rotation2 += rotationChange
    }
    var rotation3 by remember { mutableStateOf(321f) }
    val state3 = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        rotation3 += rotationChange
    }
    var rotation4 by remember { mutableStateOf(147f) }
    val state4 = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        rotation4 += rotationChange
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column() {
            TopBar(text = stringResource(R.string.title_location_bat), isMainNav = false)
            Text(text = "Drehe die Zahnräder mit zwei Fingern, um die Tür zu entschlüsseln", Modifier.padding(all = MaterialTheme.spacing.medium))
        }

        Box(modifier = Modifier .padding(all = MaterialTheme.spacing.medium)) {

            Image(
                modifier = Modifier
                    .offset(143.dp, -350.dp)
                    .size(150.dp),
                painter = painterResource(id = R.drawable.drill_1),
                contentDescription = "Drill",
            )
            Box(modifier = Modifier .offset(0.dp, -250.dp)) {
                Image(
                    modifier = Modifier
                        // apply other transformations like rotation and zoom
                        // on the pizza slice emoji
                        .graphicsLayer(
                            rotationZ = rotation2,
                        )
                        // add transformable to listen to multitouch transformation events
                        // after offset
                        .transformable(state = state2)
                        .size(180.dp),
                    painter = painterResource(id = R.drawable.drill_2),
                    contentDescription = "Drill",
                )
            }
            Box(modifier = Modifier .offset(140.dp, -125.dp)) {
                Image(
                    modifier = Modifier
                        // apply other transformations like rotation and zoom
                        // on the pizza slice emoji
                        .graphicsLayer(
                            rotationZ = rotation3,
                        )
                        // add transformable to listen to multitouch transformation events
                        // after offset
                        .transformable(state = state3)
                        .size(180.dp),
                    painter = painterResource(id = R.drawable.drill_3),
                    contentDescription = "Drill",
                )
            }
            Box(modifier = Modifier) {
                Image(
                    modifier = Modifier
                        // apply other transformations like rotation and zoom
                        // on the pizza slice emoji
                        .graphicsLayer(
                            rotationZ = rotation4,
                        )
                        // add transformable to listen to multitouch transformation events
                        // after offset
                        .transformable(state = state4)
                        .size(180.dp),
                    painter = painterResource(id = R.drawable.drill_4),
                    contentDescription = "Drill",
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun BatScreenPreview() {
    BatScreen()
}