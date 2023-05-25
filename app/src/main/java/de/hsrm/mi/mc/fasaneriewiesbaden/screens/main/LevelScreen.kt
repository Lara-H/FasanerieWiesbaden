package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun LevelScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        ){
        Row(modifier = Modifier .fillMaxWidth()) {
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut_full),
                contentDescription = "Nut",
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut_full),
                contentDescription = "Nut"
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
        }
        Row(modifier = Modifier .fillMaxWidth()) {
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
        }
        Row(modifier = Modifier .fillMaxWidth()) {
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
        }
        Row(modifier = Modifier .fillMaxWidth()) {
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
            Image(
                modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                painter = painterResource(id = R.drawable.nut),
                contentDescription = "Nut"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LevelScreenPreview() {
    LevelScreen()
}