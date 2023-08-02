package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.GoatViewModel

@Composable
fun GoatScreen(onClose: () -> Unit, onDone: () -> Unit, onFalseClick: () -> Unit) {
    val viewModel = viewModel<GoatViewModel>()

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
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TopBar(text = stringResource(R.string.title_location_goat), onClose = onClose)
            TextBox(stringResource(R.string.station_goat_game_text))
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium, bottom = MaterialTheme.spacing.medium),
                text = stringResource(R.string.station_goat_game_question),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        }

        if (!viewModel.isDone.value) {
            val currentItem = viewModel.items[viewModel.currentPoints]

            Image(
                painter = painterResource(id = currentItem.imgPath),
                contentDescription = "Goat",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(all = MaterialTheme.spacing.medium)
                )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium)
            ) {
                CreateButton(
                    modifier = Modifier .weight(1f) .padding(end = MaterialTheme.spacing.medium / 2),
                    bgColor = MaterialTheme.colorScheme.primary,
                    icon = Icons.Default.Check,
                    clickedValue = true,
                    correctValue = currentItem.isGoat,
                    viewModel = viewModel,
                    onFalseClick = onFalseClick,
                )
                CreateButton(
                    modifier = Modifier .weight(1f) .padding(start = MaterialTheme.spacing.medium / 2),
                    bgColor = MaterialTheme.colorScheme.tertiary,
                    icon = Icons.Default.Close,
                    clickedValue = false,
                    correctValue = currentItem.isGoat,
                    viewModel = viewModel,
                    onFalseClick = onFalseClick,
                )

            }
        }

        ProcessBar(
            icon = R.drawable.icon_goat,
            numberTotal = viewModel.totalPoints,
            numberFull = viewModel.currentPoints
        )
    }
}

@Composable
fun CreateButton(modifier: Modifier, bgColor: Color, icon: ImageVector, clickedValue: Boolean, correctValue: Boolean, viewModel: GoatViewModel, onFalseClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(0),
        contentPadding = PaddingValues(all = 5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
        onClick = { handleClick(clickedValue, correctValue, viewModel, onFalseClick) }
    ) {
        Icon(icon, "Icon", tint = Color.White)
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