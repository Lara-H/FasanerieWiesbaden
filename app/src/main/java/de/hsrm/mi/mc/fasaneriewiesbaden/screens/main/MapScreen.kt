package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.GoogleMaps
import de.hsrm.mi.mc.fasaneriewiesbaden.components.PrimaryButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.colorpalette
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.sizing
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MapScreen(navController: NavHostController, data: MainActivityViewModel) {

    GoogleMaps(navController, data)

    Column {
        Column(modifier = Modifier .padding(top = MaterialTheme.sizing.topBar)) {
            TextBox(stringResource(R.string.map_text), Color.White, MaterialTheme.colorpalette.alphaDark)

            if (data.nextStationButtonVisible.value) {
                Box(modifier = Modifier .padding(MaterialTheme.spacing.medium)) {
                    PrimaryButton(text = stringResource(R.string.map_station_btn), onClick = { handleClick(navController, data) })
                }
            }

            // TODO: ONLY FOR DEVELOPMENT
            Button(
                modifier = Modifier .padding(MaterialTheme.spacing.medium),
                onClick = { handleClick(navController, data) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Text(text = "DEV: Nächste Station öffnen")
            }
            ///////////////////////////////

        }


    }

}

fun handleClick(navController: NavHostController, data: MainActivityViewModel) {
    if (data.gameDone.value) {
        navController.navigate(Graph.OUTRO)
    } else {
        navController.navigate(data.stations[data.nextStationKey.value].graph)
    }
}



