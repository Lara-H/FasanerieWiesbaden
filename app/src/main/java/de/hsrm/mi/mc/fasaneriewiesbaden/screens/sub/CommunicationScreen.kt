package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunicationScreen(data: MainActivityViewModel, title: String? = null, imagePath: Int? = null, text: String, btnText: String? = null, onBtnClick: () -> Unit, onClose: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(text=createTitle(data, title), onClose = onClose)
        },
    ) {
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = createImgPath(data, imagePath)),
            contentDescription = createTitle(data, title),
            modifier = Modifier
                .align(Alignment.End)
        )
        val scroll = rememberScrollState(0)
        Text(
            modifier = Modifier
                .background(color = Color.White)
                .padding(all = MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(200.dp)
                .verticalScroll(scroll),
            text = text,
            color = MaterialTheme.colorScheme.onBackground
        )

        BottomButton(
            onClick = { onBtnClick() },
            text = createBtnText(btnText),
        )
    }
}

@Composable
fun createTitle(data: MainActivityViewModel, title: String?): String {
    var newTitle = data.stations[data.nextStationKey.value].animalName.asString()
    if (title != null) {
        return title
    }
    return newTitle
}

@Composable
fun createImgPath(data: MainActivityViewModel, imagePath: Int?): Int {
    var newImgPath = data.stations[data.nextStationKey.value].imgPath
    if (imagePath != null) {
        return imagePath
    }
    return newImgPath
}

@Composable
fun createBtnText(btnText: String?): String {
    var newBtnText = stringResource(R.string.communication_btn)
    if (btnText != null) {
        return btnText
    }
    return newBtnText
}

@Preview(showBackground = true)
@Composable
fun CommunicationScreenPreview() {
    CommunicationScreen(
        data = MainActivityViewModel(),
        text = "Lorem ipsum dolor sit amet",
        onBtnClick = {},
        onClose = {},
    )
}