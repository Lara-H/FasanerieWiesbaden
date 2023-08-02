package de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Sizing(
    val topBar: Dp = 64.dp,
    val bottomBar: Dp = 70.dp,
    val bottomButton: Dp = 70.dp,
    val progressBar: Dp = 50.dp,
)

val LocalSizing = compositionLocalOf { Sizing() }

val MaterialTheme.sizing: Sizing
    @Composable
    @ReadOnlyComposable
    get() = LocalSizing.current