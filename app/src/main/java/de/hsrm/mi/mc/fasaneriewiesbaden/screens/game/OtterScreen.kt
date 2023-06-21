package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.ProcessBar
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import java.util.Timer
import kotlin.concurrent.schedule

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtterScreen() {
    var currentPoints by remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFC8DBEB)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var offsetX by remember { mutableStateOf((-150).dp) }
        var offsetY by remember { mutableStateOf(0.dp) }

        if (offsetX < screenWidth) {
            Timer().schedule(200) {
                offsetX += 15.dp
            }
        } else {
            offsetX = (-150).dp
            offsetY = ((-150)..150).shuffled().last().dp
        }

        Column {
            TopBar(text = stringResource(R.string.title_location_otter), isMainNav = false)
            Text(text = "Tippe auf die Fische, um sie zu fangen", modifier = Modifier .padding(all = MaterialTheme.spacing.medium))
        }

        Image(
            painter = painterResource(
                id = R.drawable.fish),
            contentDescription = "Fish",
            modifier = Modifier
                .size(150.dp)
                .animatePlacement()
                .offset(x = offsetX, y = offsetY)
                .clickable { currentPoints++; offsetX = screenWidth }
        )

        ProcessBar(
            icon = Icons.Default.Person,
            numberTotal = 5,
            numberFull = currentPoints
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OtterScreenPreview() {
    OtterScreen()
}