package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BatScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val viewModel = viewModel<BatViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BatViewModel(
                    screenWidth = screenWidth,
                ) as T
            }
        }
    )

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column() {
            TopBar(text = stringResource(R.string.title_location_bat), isMainNav = false)
            Text(text = "Drehe die Zahnräder mit zwei Fingern, um die Tür zu entschlüsseln", Modifier.padding(all = MaterialTheme.spacing.medium))
        }

        Box(modifier = Modifier .padding(all = MaterialTheme.spacing.medium)) {

            viewModel.drills.forEach() {
                Box(modifier = Modifier .offset(it.offsetX, it.offsetY)) {
                    Image(
                        modifier = Modifier
                            .graphicsLayer(
                                rotationZ = it.rotation,
                            )
                            .transformable(state = rememberTransformableState { _, _, rotationChange ->
                                viewModel.rotate(it.id, rotationChange) }
                            )
                            .size(it.size),
                        painter = painterResource(id = it.imgPath),
                        contentDescription = "Drill",
                    )
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun BatScreenPreview() {
    BatScreen()
}