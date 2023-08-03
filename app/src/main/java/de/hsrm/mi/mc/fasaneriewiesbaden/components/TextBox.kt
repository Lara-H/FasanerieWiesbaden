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
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun TextBox(text: String, colorText: Color? = null, colorBg: Color? = null) {
    Box(modifier = Modifier .background(createColorBg(colorBg))) {
        Text(
            modifier = Modifier .fillMaxWidth() .padding(all = MaterialTheme.spacing.medium),
            text = text, color = createColorText(colorText)
        )
    }
}

@Composable
fun createColorText(colorText: Color?): Color {
    val newColorText = MaterialTheme.colorScheme.onBackground
    if (colorText != null) {
        return colorText
    }
    return newColorText
}

@Composable
fun createColorBg(colorBg: Color?): Color {
    val newColorBg = Color.Transparent
    if (colorBg != null) {
        return colorBg
    }
    return newColorBg
}

@Preview(showBackground = true)
@Composable
fun TextBoxPreview() {
    TextBox(
        text = "Lorem ipsum dolor sit amet",
        colorBg = Color.Black,
        colorText = Color.White
    )
}