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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoatScreen(isCorrect: Boolean, imagePath: Int, onCorrectClick: () -> Unit, onFalseClick: () -> Unit) {
    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.title_location_goat), isMainNav = false) },
    ) {
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier
            .padding(all = MaterialTheme.spacing.medium),) {
            Text(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.medium),
                text = stringResource(R.string.station_goat_game_text),
            )
            Text(
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.medium),
                text = stringResource(R.string.station_goat_game_question),
            )
        }
        Column(modifier = Modifier
            .padding(all = MaterialTheme.spacing.medium),
        ) {
            Image(
                painter = painterResource(
                id = imagePath),
                contentDescription = "Animal",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = MaterialTheme.spacing.large)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(end = MaterialTheme.spacing.medium / 2),
                    onClick = { handleClick(true, isCorrect, onCorrectClick, onFalseClick) }) {
                    Icon(Icons.Default.Check, "Icon", tint = Color.White)
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .padding(start = MaterialTheme.spacing.medium / 2),
                    onClick = { handleClick(false, isCorrect, onCorrectClick, onFalseClick) }) {
                    Icon(Icons.Default.Close, "Icon", tint = Color.White)
                }
            }
        }
        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = 5,
            numberFull = 1
        )
    }
}

fun handleClick(clickedValue: Boolean, correctValue: Boolean, onCorrectClick: () -> Unit, onFalseClick: () -> Unit) {
    if (clickedValue == correctValue) {
        onCorrectClick()
    }
    if (clickedValue != correctValue) {
        onFalseClick()
    }
}

@Preview(showBackground = true)
@Composable
fun GoatScreenPreview() {
    GoatScreen(
        isCorrect = false,
        imagePath = R.drawable.game_goat_1,
        onCorrectClick = {},
        onFalseClick = {}
    )
}