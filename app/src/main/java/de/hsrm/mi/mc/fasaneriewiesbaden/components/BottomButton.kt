package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomButton(text: String, onClick: () -> Unit) {

    // haptic feedback
    val haptic = LocalHapticFeedback.current

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        onClick = { onClick(); haptic.performHapticFeedback(HapticFeedbackType.LongPress) }
    ) {
        Text(
            text = text.uppercase(),
            modifier = Modifier .padding(all = 10.dp),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun BottomButtonPreview() {
    BottomButton(
        text = "Weiter",
        onClick = {}
    )
}