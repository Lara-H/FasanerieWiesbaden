package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun ProcessBar(icon: ImageVector, numberTotal: Int, numberFull: Int) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .background(Color.DarkGray)
        .padding(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(numberTotal) { index ->
            var color = Color.White
            if (index < numberFull) {
                color = Color.Magenta
            }
            Icon(
                imageVector = icon,
                contentDescription = "Icon",
                modifier = Modifier,
                tint = color
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProcessBarPreview() {
    ProcessBar(
        icon = Icons.Default.Person,
        numberTotal = 5,
        numberFull = 1
    )
}