package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.sizing
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun InfoScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background))
    Column(
        modifier = Modifier
            .padding(top = MaterialTheme.sizing.topBar, bottom = MaterialTheme.sizing.bottomBar)
            .padding(all = MaterialTheme.spacing.medium)
    ) {
        Text(text = stringResource(R.string.info_headline), color = MaterialTheme.colorScheme.tertiary, fontWeight = FontWeight.Bold)
        Row(modifier = Modifier .fillMaxWidth() .padding(top = MaterialTheme.spacing.medium)) {
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_time_early), color = MaterialTheme.colorScheme.onBackground)
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_animal_bear), color = MaterialTheme.colorScheme.onBackground)
                Text(text = stringResource(R.string.info_animal_wolf), color = MaterialTheme.colorScheme.onBackground)
                Text(text = stringResource(R.string.info_animal_lynx), color = MaterialTheme.colorScheme.onBackground)
            }
        }
        Row(modifier = Modifier .fillMaxWidth() .padding(top = MaterialTheme.spacing.medium)) {
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_time_late), color = MaterialTheme.colorScheme.onBackground)
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_animal_fox), color = MaterialTheme.colorScheme.onBackground)
                Text(text = stringResource(R.string.info_animal_racoon), color = MaterialTheme.colorScheme.onBackground)
                Text(text = stringResource(R.string.info_animal_racoondog), color = MaterialTheme.colorScheme.onBackground)
                Text(text = stringResource(R.string.info_animal_wildcat), color = MaterialTheme.colorScheme.onBackground)
                Text(text = stringResource(R.string.info_animal_otter), color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen()
}