package de.hsrm.mi.mc.fasaneriewiesbaden

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InfoScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Info")
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen()
}