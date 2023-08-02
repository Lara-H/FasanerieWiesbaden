package de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val green_200 = Color(0xFFD7D68C)
val green_400 = Color(0xFFBDC558)
val green_500 = Color(0xFFB2B200)
val green_700 = Color(0xFF7E9668)
val green_900 = Color(0xFF406925)
val red_500 = Color(0xFF973710)
val beige_100 = Color(0xFFFAF4D8)
val beige_500 = Color(0xFFF4D090)
val gray_500 = Color(0xFF9C9C9C)
val gray_600 = Color(0xFF817789)
val gray_700 = Color(0xFF68636b)
val gray_900 = Color(0xFF333333)
val blue_500 = Color(0xFFC8DBEB)
val alphaDark = Color (0x99000000)

data class ColorPalette(
    val green_200: Color = Color(0xFFD7D68C),
    val green_400: Color = Color(0xFFBDC558),
    val green_500: Color = Color(0xFFB2B200),
    val green_700: Color = Color(0xFF7E9668),
    val green_900: Color = Color(0xFF406925),
    val red_500: Color = Color(0xFF973710),
    val beige_100: Color = Color(0xFFFAF4D8),
    val beige_500: Color = Color(0xFFF4D090),
    val gray_500: Color = Color(0xFF9C9C9C),
    val gray_600: Color = Color(0xFF817789),
    val gray_700: Color = Color(0xFF68636b),
    val gray_900: Color = Color(0xFF333333),
    val blue_500: Color = Color(0xFFC8DBEB),
    val alphaDark: Color = Color (0x99000000),
)

val LocalColorPalette = compositionLocalOf { ColorPalette() }

val MaterialTheme.colorpalette: ColorPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalColorPalette.current
