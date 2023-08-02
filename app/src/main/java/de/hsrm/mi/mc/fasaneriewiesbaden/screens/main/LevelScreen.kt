package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LevelScreen(data: MainActivityViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 70.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ){

        var i = 0
        val itemsPerLine = 4

        if (data.stations.isNotEmpty()) {
            while (i < data.stations.size) {
                Row(modifier = Modifier .fillMaxWidth()) {
                    var posInLine = 0
                    while ((posInLine < itemsPerLine)) {
                        if (i < data.stations.size) {
                            val item = data.stations[i]
                            if (item.isDone) {
                                Image(
                                    modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                                    painter = painterResource(id = R.drawable.nut_full),
                                    contentDescription = "Nut",
                                    contentScale = ContentScale.Fit
                                )
                            } else {
                                Image(
                                    modifier = Modifier .weight(1f) .padding(all = MaterialTheme.spacing.medium),
                                    painter = painterResource(id = R.drawable.nut),
                                    contentDescription = "Nut",
                                    contentScale = ContentScale.Fit
                                )
                            }
                        } else {
                            Box(modifier = Modifier .weight(1f))
                        }
                        i++
                        posInLine++
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LevelScreenPreview() {
    LevelScreen(
        data = MainActivityViewModel(),
    )
}