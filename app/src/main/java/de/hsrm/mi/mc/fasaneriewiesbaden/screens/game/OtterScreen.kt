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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtterScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val viewModel = viewModel<OtterViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return OtterViewModel(
                    screenWidth = screenWidth
                ) as T
            }
        }
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFC8DBEB)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel.moveFish()
                mainHandler.postDelayed(this, 1000)
                mainHandler.removeCallbacks(this)
            }
        })

        Column {
            TopBar(text = stringResource(R.string.title_location_otter), isMainNav = false)
            Text(text = "Tippe auf die Fische, um sie zu fangen", modifier = Modifier .padding(all = MaterialTheme.spacing.medium))
            Button(onClick = { viewModel.moveFish() }) {
                Text(text = "Bg Color")

            }
        }

        Image(
            painter = painterResource(
                id = R.drawable.fish),
            contentDescription = "Fish",
            modifier = Modifier
                .size(150.dp)
                .animatePlacement()
                .offset(x = viewModel.fish.offsetX, y = viewModel.fish.offsetY)
                .clickable { viewModel.addPoint() }
        )

        ProcessBar(
            icon = Icons.Default.Person,
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