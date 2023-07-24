package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoatScreen(onClose: () -> Unit, onDone: () -> Unit, onFalseClick: () -> Unit) {

    // viewmodel
    val viewModel = viewModel<GoatViewModel>()

    // check if done
    if (viewModel.isDone.value) {
        onDone()
    }

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TopBar(text = stringResource(R.string.title_location_goat), onClose = onClose)
            Text(
                modifier = Modifier .padding(all = MaterialTheme.spacing.medium),
                text = stringResource(R.string.station_goat_game_text),
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium, bottom = MaterialTheme.spacing.medium),
                text = stringResource(R.string.station_goat_game_question),
                fontWeight = FontWeight.Bold
            )
        }

        if (!viewModel.isDone.value) {
            val currentItem = viewModel.items[viewModel.currentPoints]

            Image(
                painter = painterResource(id = currentItem.imgPath),
                contentDescription = viewModel.goatImgAltText,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(all = MaterialTheme.spacing.medium)
                )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium)
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(end = MaterialTheme.spacing.medium / 2),
                shape = RoundedCornerShape(0),
                    onClick = { handleClick(true, currentItem.isGoat, viewModel, onFalseClick) }) {
                    Icon(Icons.Default.Check, "Icon", tint = Color.White)
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(start = MaterialTheme.spacing.medium / 2),
                    shape = RoundedCornerShape(0),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                    onClick = { handleClick(false, currentItem.isGoat, viewModel, onFalseClick) }) {
                    Icon(Icons.Default.Close, "Icon", tint = Color.White)
                }
            }
        }

        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = viewModel.totalPoints,
            numberFull = viewModel.currentPoints
        )
    }
}

fun handleClick(clickedValue: Boolean, correctValue: Boolean, viewModel: GoatViewModel, onFalseClick: () -> Unit) {
    if (clickedValue == correctValue) {
        viewModel.addPoint()
    }
    if (clickedValue != correctValue) {
        onFalseClick()
    }
}

@Preview(showBackground = true)
@Composable
fun GoatScreenPreview() {
    GoatScreen(
        onDone = {},
        onClose = {},
        onFalseClick = {}
    )
}