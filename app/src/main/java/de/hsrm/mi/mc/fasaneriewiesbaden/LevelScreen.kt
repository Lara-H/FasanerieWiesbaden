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
fun LevelScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(all = MaterialTheme.spacing.medium)){
        Text(text = "Level")
        Row(modifier = Modifier .fillMaxWidth()) {
            Column(modifier = Modifier .weight(1f)) {
                Text(text = "Nut")
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = "Nut")
            }
            Column(modifier = Modifier .weight(1f)) {
                Text(text = "Nut")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LevelScreenPreview() {
    LevelScreen()
}