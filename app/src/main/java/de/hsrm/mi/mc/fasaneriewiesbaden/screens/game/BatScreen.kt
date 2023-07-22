package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import kotlinx.coroutines.delay
import java.util.Timer
import kotlin.concurrent.timer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BatScreen() {
    val configuration = LocalConfiguration.current
    val doorWidth = configuration.screenWidthDp.dp - (MaterialTheme.spacing.medium * 2)

    val viewModel = viewModel<BatViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BatViewModel(
                    doorWidth = doorWidth,
                ) as T
            }
        }
    )

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    // open door
    val mainHandler = Handler(Looper.getMainLooper())
    if (viewModel.gameIsDone.value && !viewModel.isDone.value) {
        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel.openDoor()
                mainHandler.postDelayed(this, 5000)
                mainHandler.removeCallbacks(this)
            }
        })
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column() {
            TopBar(text = stringResource(R.string.title_location_bat), isMainNav = false)
            Text(text = "Drehe die Zahnräder mit zwei Fingern, um die Tür zu entschlüsseln", Modifier.padding(all = MaterialTheme.spacing.medium), color = Color.White,)
        }

        Box(
            modifier = Modifier
                .padding(all = MaterialTheme.spacing.medium)
                .fillMaxSize()
                .offset(viewModel.offsetDoor.value)
                .paint(
                    painterResource(id = R.drawable.bat_door),
                    contentScale = ContentScale.FillBounds
                )
                .border(width = 1.dp, color = Color(0xFF817789))
            ) {

            viewModel.drills.forEach() {
                Box(modifier = Modifier .offset(it.offsetX, it.offsetY)) {

                    val alpha: Float by animateFloatAsState(if (viewModel.visible.value) 0.75f else 0.5f)
                    Image(
                        modifier = Modifier
                            .graphicsLayer(
                                rotationZ = it.rotation,
                            )
                            .transformable(state = rememberTransformableState { _, _, rotationChange ->
                                viewModel.rotate(it.id, rotationChange)
                            }
                            )
                            .size(it.size),
                        painter = painterResource(id = it.imgPath),
                        contentDescription = "Drill",
                        alpha = alpha
                    )
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun BatScreenPreview() {
    BatScreen()
}