package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(text: String, isMainNav: Boolean) {
    TopAppBar(
        title = { Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        actions = {
            if (isMainNav) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            } else {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Close, "Close")
                }
            }
        },
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(text = "Hello World", isMainNav = true)
}