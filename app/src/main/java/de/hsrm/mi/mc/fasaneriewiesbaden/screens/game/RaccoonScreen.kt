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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RaccoonScreen() {

    var isSelected by remember { mutableStateOf(false)}

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
            TopBar(text = stringResource(R.string.title_location_raccoon), isMainNav = false)
            Text(
                modifier = Modifier .padding(all = MaterialTheme.spacing.medium),
                text = stringResource(R.string.station_raccoon_game_text)
            )

        Row() {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .clickable { isSelected = !isSelected }
                    .background(if (isSelected) { Color.Red } else { Color.Green })
                    .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut_full),
                contentDescription = "Item",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut_full),
                contentDescription = "Item",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut_full),
                contentDescription = "Item",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .weight(1f)
                    .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut_full),
                contentDescription = "Item",
                contentScale = ContentScale.Fit
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RaccoonScreenPreview() {
    RaccoonScreen()
}