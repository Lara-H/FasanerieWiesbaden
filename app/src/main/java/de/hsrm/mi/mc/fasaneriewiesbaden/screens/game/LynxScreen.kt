package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LynxScreen() {

    val viewModel = viewModel<LynxViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LynxViewModel() as T
            }
        }
    )

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    // haptic feedback
    val haptic = LocalHapticFeedback.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFC8DBEB)),
    ) {

        Column {
            TopBar(text = stringResource(R.string.title_location_lynx), isMainNav = false)
            Text(text = stringResource(R.string.station_lynx_game_text), modifier = Modifier .padding(all = MaterialTheme.spacing.medium))
        }

        Column {

            var i = 0
            val itemsPerLine = 2

            if (viewModel.items.isNotEmpty()) {
                while (i < viewModel.items.size) {
                    Row(modifier = Modifier .fillMaxWidth()) {
                        var posInLine = 0
                        while ((posInLine < itemsPerLine)) {
                            if ((i < viewModel.items.size)) {
                                val item = viewModel.items[i]
                                Image(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { viewModel.handleClick(item); haptic.performHapticFeedback(
                                            HapticFeedbackType.LongPress)  }
                                        .padding(all = MaterialTheme.spacing.medium),
                                    painter = painterResource(id = item.imgPath),
                                    contentDescription = "Item",
                                    contentScale = ContentScale.Fit
                                )
                            } else {
                                Box(modifier = Modifier .weight(1f))
                            }
                            i++
                            posInLine++
                        }
                    }
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun LynxScreenPreview() {
    LynxScreen()
}