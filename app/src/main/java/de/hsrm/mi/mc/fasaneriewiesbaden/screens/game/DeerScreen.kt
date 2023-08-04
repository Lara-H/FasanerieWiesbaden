@file:Suppress("UNCHECKED_CAST")

package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.DeerViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun DeerScreen(onClose: () -> Unit, onDone: () -> Unit) {
    val context = LocalContext.current

    // viewModel
    val viewModel = viewModel<DeerViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DeerViewModel(
                    onDone = onDone,
                    appContext = context
                ) as T
            }
        }
    )

    Scaffold(
        topBar = { TopBar(text=stringResource(R.string.title_location_deer), onClose = onClose) },
    ) {
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(painter = painterResource(
            id = R.drawable.deer),
            contentDescription = "Deer",
            modifier = Modifier
                .align(Alignment.End)
        )
        val scroll = rememberScrollState(0)
        val barcodeResults = viewModel.barCodeResults.collectAsState()
        val scope = rememberCoroutineScope()

        Text(
            modifier = Modifier
                .background(color = Color.White)
                .padding(all = MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(200.dp)
                .verticalScroll(scroll),
            text = barcodeResults.value ?: stringResource(R.string.station_deer_game_text),
            color = MaterialTheme.colorScheme.onBackground
        )

        BottomButton(
            onClick = {
                scope.launch {
                    viewModel.startScan()
                }
            },
            text = stringResource(R.string.station_deer_game_btn),
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DeerScreenPreview() {
    DeerScreen(
        onClose = {},
        onDone = {}
    )
}