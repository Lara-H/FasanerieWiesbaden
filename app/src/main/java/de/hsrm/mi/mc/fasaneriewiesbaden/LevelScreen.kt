package de.hsrm.mi.mc.fasaneriewiesbaden

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LevelScreen() {
    var num = 10
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(all = 20.dp)){
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