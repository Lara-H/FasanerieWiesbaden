package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(viewModel: MainViewModel) {

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    if (viewModel.isExpanded.value) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000))
            .clickable { viewModel.changeExpanded() }
        )
    }

    Column(
        modifier = Modifier .fillMaxHeight(),
        horizontalAlignment = Alignment.End
    ) {
        TopAppBar(
            title = { Text(
                text = viewModel.topBarTitle.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            ) },
            colors = androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            actions = {
                IconButton(onClick = { viewModel.changeExpanded() }) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            },
        )
        if (viewModel.isExpanded.value) {
            NavMenu(viewModel)
        }
    }
}

@Composable
fun NavMenu(viewModel: MainViewModel) {
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxHeight()
        .padding(all = MaterialTheme.spacing.medium)
        .defaultMinSize(minWidth = 200.dp)

    ) {



        TextButton(onClick = { viewModel.changeLanguage() }) {
            Row {
                Text(text = "DE")
                Text(text = " | ")
                Text(text = "EN")
            }
        }
        TextButton(onClick = { viewModel.resetGame() }) {
            Text(text = "Zurücksetzen".uppercase())
        }
    }
}

@Preview
@Composable
fun MainTopBarPreview() {
    MainTopBar(
        viewModel = MainViewModel()
    )
}
