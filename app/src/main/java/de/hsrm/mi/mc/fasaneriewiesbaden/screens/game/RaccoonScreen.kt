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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.colorpalette
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.RaccoonViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RaccoonScreen(onClose: () -> Unit, onDone: () -> Unit) {
    val viewModel = viewModel<RaccoonViewModel>()

    // check if done
    if (viewModel.isDone.value) {
        LaunchedEffect(Unit) {
            onDone()
        }
    }

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorpalette.gray_500),
        verticalArrangement = Arrangement.Top
    ) {
            TopBar(text = stringResource(R.string.title_location_raccoon), onClose = onClose)
            TextBox(text = stringResource(R.string.station_raccoon_game_text), Color.White, MaterialTheme.colorpalette.alphaDark)

        Column {

            var i = 0
            val itemsPerLine = 3

            if (viewModel.items.isNotEmpty()) {
                while (i < viewModel.items.size) {
                    Row(modifier = Modifier .fillMaxWidth()) {
                        var posInLine = 0
                        while ((posInLine < itemsPerLine)) {
                            if ((i < viewModel.items.size) && (!viewModel.items[i].isFound)) {
                                val item = viewModel.items[i]
                                Image(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { viewModel.handleClick(item) }
                                        .background(
                                            if ((item.id == viewModel.selectedFirst.value.id) || (item.id == viewModel.selectedSecond.value.id))  {
                                                viewModel.selectedColor.value
                                            } else {
                                                Color.Transparent
                                            }
                                        )
                                        .padding(all = MaterialTheme.spacing.medium),
                                    painter = painterResource(id = item.imgPath),
                                    contentDescription = "Sock",
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
fun RaccoonScreenPreview() {
    RaccoonScreen(
        onClose = {},
        onDone = {}
    )
}