package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun TextBox(text: String, colorText: Color, colorBg: Color) {
    Box(modifier = Modifier .background(colorBg)) {
        Text(
            modifier = Modifier .fillMaxWidth() .padding(top = 64.dp) .padding(all = MaterialTheme.spacing.medium),
            text = text, color = colorText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextBoxPreview() {
    TextBox(
        text = "Bewege dich von deinem Standort aus zum grünen Punkt auf der Karte",
        colorBg = Color.Black,
        colorText = Color.White
    )
}