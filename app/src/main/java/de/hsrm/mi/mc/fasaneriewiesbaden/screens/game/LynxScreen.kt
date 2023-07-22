package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LynxScreen(onDone: () -> Unit) {

    // viewmodel
    val viewModel = viewModel<LynxViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LynxViewModel() as T
            }
        }
    )

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    // check if done
    if (viewModel.isDone.value) {
        onDone()
    }

    // haptic feedback
    val haptic = LocalHapticFeedback.current

    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.background),
    ) {

        Column {
            TopBar(text = stringResource(R.string.title_location_lynx), isMainNav = false)
            Text(text = stringResource(R.string.station_lynx_game_text), modifier = Modifier .padding(all = MaterialTheme.spacing.medium))
        }

        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(all = MaterialTheme.spacing.medium/2),
            ) {

            var i = 0
            val itemsPerLine = 2

            if (viewModel.items.isNotEmpty()) {
                while (i < viewModel.items.size) {
                    Row(modifier = Modifier .fillMaxWidth()) {
                        var posInLine = 0
                        while ((posInLine < itemsPerLine)) {
                            if ((i < viewModel.items.size)) {
                                val item = viewModel.items[i]
                                Box(modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium/2)) {
                                    Column {
                                        Row(modifier = Modifier .padding(bottom = MaterialTheme.spacing.extraSmall)) {
                                            Text(modifier = Modifier .padding(end = MaterialTheme.spacing.extraSmall), text = "Bild Nr.")
                                            Image(
                                                modifier = Modifier .size(20.dp),
                                                painter = painterResource(id = item.imgCube),
                                                contentDescription = viewModel.itemImgAltText
                                            )
                                        }
                                        Image(
                                            modifier = Modifier
                                                .clickable {
                                                    viewModel.handleClick(item); haptic.performHapticFeedback(
                                                    HapticFeedbackType.LongPress
                                                ) },
                                            painter = painterResource(id = item.imgPath),
                                            contentDescription = viewModel.itemImgAltText,
                                        )
                                    }
                                }
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
    LynxScreen(onDone = {})
}