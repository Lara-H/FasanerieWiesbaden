package de.hsrm.mi.mc.fasaneriewiesbaden.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(viewModel: MainViewModel, data: MainActivityViewModel) {

    // device width
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // detect any changes to data and recompose composable
    viewModel.onUpdate.value

    // dark background when expanded
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
                IconButton(onClick = {
                    viewModel.changeExpanded()
                }) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            },
        )
        AnimatedVisibility(
            visible = viewModel.isExpanded.value,
            enter = expandHorizontally() + fadeIn(),
            exit = shrinkHorizontally() + fadeOut()

        ) {
            NavMenu(viewModel, data, screenWidth)
         }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMenu(viewModel: MainViewModel, data: MainActivityViewModel, screenWidth: Dp) {
    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxHeight()
        .padding(all = MaterialTheme.spacing.medium)
        .width(screenWidth - 100.dp)

    ) {

        // Reset
        Text(text = stringResource(R.string.maintopbar_reset_headline), fontWeight = FontWeight.Bold)
        Text(
            modifier = Modifier .padding(top = MaterialTheme.spacing.extraSmall),
            text = stringResource(R.string.maintopbar_reset_text),
        )
        var isError by remember { mutableStateOf(false) }
        var textInput by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            modifier = Modifier .padding(top = MaterialTheme.spacing.medium, bottom = MaterialTheme.spacing.medium),
            singleLine = true,
            value = textInput,
            isError = isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(errorBorderColor = Red, containerColor = Color.White, textColor = MaterialTheme.colorScheme.onBackground),
            onValueChange = { newText ->
                textInput = newText
            }
        )
        PrimaryButton(text = stringResource(R.string.maintopbar_reset_btn), onClick = {
            if (textInput.text == "RESET") {
                data.resetGame()
                isError = false
                viewModel.changeExpanded()
            } else {
                isError = true
            }
            textInput = TextFieldValue("")
        })
    }
}

@Preview
@Composable
fun MainTopBarPreview() {
    MainTopBar(
        viewModel = MainViewModel(),
        data = MainActivityViewModel()
    )
}
