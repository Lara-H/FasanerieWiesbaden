package de.hsrm.mi.mc.fasaneriewiesbaden

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LevelScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Level")
    }
}

@Preview(showBackground = true)
@Composable
fun LevelScreenPreview() {
    LevelScreen()
}