package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import androidx.compose.runtime.LaunchedEffect
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.OtterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtterScreen(onClose: () -> Unit, onDone: () -> Unit) {
    // screen size
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // viewmodel
    val viewModel = viewModel<OtterViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return OtterViewModel(
                    screenWidth = screenWidth,
                ) as T
            }
        }
    )

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    // check if done
    if (viewModel.isDone.value) {
        LaunchedEffect(Unit) {
            onDone()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFC8DBEB)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        // move fishes
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel.moveFishes()
                mainHandler.postDelayed(this, 1000)
                mainHandler.removeCallbacks(this)
            }
        })

        Column {
            TopBar(text = stringResource(R.string.title_location_otter), onClose = onClose)
            TextBox(text = stringResource(R.string.station_otter_game_text))
        }

        viewModel.fishes.forEach {
            Image(
                painter = painterResource(
                    id = viewModel.fishImgPath),
                contentDescription = viewModel.fishImgAltText,
                modifier = Modifier
                    .size(viewModel.fishImgSize.dp)
                    .animatePlacement()
                    .offset(x = it.offsetX, y = it.offsetY)
                    .clickable { viewModel.addPoint() }
            )
        }

        ProcessBar(
            icon = R.drawable.icon_otter,
            numberTotal = viewModel.totalPoints,
            numberFull = viewModel.currentPoints
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OtterScreenPreview() {
    OtterScreen(
        onClose = {},
        onDone = {}
    )
}