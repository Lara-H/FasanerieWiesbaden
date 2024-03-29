package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(text: String, onClose: () -> Unit) {

    // haptic feedback
    val haptic = LocalHapticFeedback.current

    TopAppBar(
        title = { Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        colors = androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = { onClose(); haptic.performHapticFeedback(HapticFeedbackType.LongPress) }) {
                Icon(Icons.Default.Close, "Close")
            }
        },
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        text = "Hello World",
        onClose = {}
    )
}