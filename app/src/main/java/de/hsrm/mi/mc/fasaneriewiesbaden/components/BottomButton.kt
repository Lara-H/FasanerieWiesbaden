package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.sizing

@Composable
fun BottomButton(text: String, onClick: () -> Unit) {

    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.sizing.bottomButton)
            .background(MaterialTheme.colorScheme.primary),
        onClick = { onClick() }
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
        text = "Lorem ipsum",
        onClick = {}
    )
}