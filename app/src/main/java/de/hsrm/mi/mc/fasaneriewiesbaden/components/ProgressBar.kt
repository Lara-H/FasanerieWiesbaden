package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@Composable
fun ProcessBar(icon: Int, numberTotal: Int, numberFull: Int) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .background(Color.DarkGray)
        .padding(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(numberTotal) { index ->
            var color = Color.Gray
            if (index < numberFull) {
                color = Color.White
            }
            Icon(
                painterResource(id = icon),
                modifier = Modifier.size(30.dp),
                contentDescription = "Icon",
                tint = color
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProcessBarPreview() {
    ProcessBar(
        icon = R.drawable.icon_map,
        numberTotal = 5,
        numberFull = 1
    )
}