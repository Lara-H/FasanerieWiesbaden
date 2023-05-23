package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomButton(text: String) {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        onClick = { /*TODO*/ }) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun BottomButtonPreview() {
    BottomButton(text = "Hello World")
}