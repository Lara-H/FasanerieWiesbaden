package de.hsrm.mi.mc.fasaneriewiesbaden

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
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun InfoScreen() {
    Column(modifier = Modifier .fillMaxSize() .padding(all = MaterialTheme.spacing.medium)) {
        Text(text = stringResource(R.string.info_headline))
        Row(modifier = Modifier .fillMaxWidth() .padding(top = MaterialTheme.spacing.medium)) {
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_time_early))
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_animal_bear))
                Text(text = stringResource(R.string.info_animal_wolf))
                Text(text = stringResource(R.string.info_animal_lynx))
            }
        }
        Row(modifier = Modifier .fillMaxWidth() .padding(top = MaterialTheme.spacing.medium)) {
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_time_late))
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = stringResource(R.string.info_animal_fox))
                Text(text = stringResource(R.string.info_animal_racoon))
                Text(text = stringResource(R.string.info_animal_racoondog))
                Text(text = stringResource(R.string.info_animal_wildcat))
                Text(text = stringResource(R.string.info_animal_otter))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen()
}