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
import androidx.compose.material.icons.Icons
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
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Star

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtterScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val imgSize = 150

    val viewModel = viewModel<OtterViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return OtterViewModel(
                    screenWidth = screenWidth,
                    imgSize = imgSize
                ) as T
            }
        }
    )

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

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
            TopBar(text = stringResource(R.string.title_location_otter), isMainNav = false)
            Text(text = stringResource(R.string.station_otter_game_text), modifier = Modifier .padding(all = MaterialTheme.spacing.medium))
        }

        viewModel.fishes.forEach {
            Image(
                painter = painterResource(
                    id = R.drawable.fish),
                contentDescription = "Fish",
                modifier = Modifier
                    .size(imgSize.dp)
                    .animatePlacement()
                    .offset(x = it.offsetX, y = it.offsetY)
                    .clickable { viewModel.addPoint() }
            )
        }

        ProcessBar(
            icon = Icons.Rounded.Star,
            numberTotal = 5,
            numberFull = viewModel.currentPoints
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OtterScreenPreview() {
    OtterScreen()
}