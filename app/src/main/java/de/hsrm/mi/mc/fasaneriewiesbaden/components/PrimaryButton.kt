package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, bgColor: Color? = null) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(0),
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = createBgColor(bgColor)),
    ) {
        Text(text = text.uppercase(), color = Color.White)
    }
}

@Composable
fun createBgColor(bgColor: Color?): Color {
    val newBgColor = MaterialTheme.colorScheme.primary
    if (bgColor != null) {
        return bgColor
    }
    return newBgColor
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "Hello World",
        onClick = {}
    )
}