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
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun InfoScreen() {
    Column(modifier = Modifier .fillMaxSize() .padding(all = MaterialTheme.spacing.medium)) {
        Text(text = "Täglich außer freitags")
        Row(modifier = Modifier .fillMaxWidth() .padding(top = MaterialTheme.spacing.medium)) {
            Column(modifier = Modifier .weight(1f)) {
                Text(text = "11:00 Uhr")
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = "Bär")
                Text(text = "Wolf")
                Text(text = "Luchs")
            }
        }
        Row(modifier = Modifier .fillMaxWidth() .padding(top = MaterialTheme.spacing.medium)) {
            Column(modifier = Modifier .weight(1f)) {
                Text(text = "15:00 Uhr")
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = "Fuchs")
                Text(text = "Waschbär")
                Text(text = "Marderhund")
                Text(text = "Wildkatze")
                Text(text = "Fischotter")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen()
}