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
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunicationScreen(title: String, imagePath: Int, imageDescription: String, text: String, btnText: String, onBtnClick: () -> Unit, onClose: () -> Unit) {

    Scaffold(
        topBar = { TopBar(text=title, onClose = onClose) },
    ) {
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(painter = painterResource(
            id = imagePath),
            contentDescription = imageDescription,
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
            text = btnText,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommunicationScreenPreview() {
    CommunicationScreen(
        title = "Elli Eichhorn",
        imagePath = R.drawable.squirrel,
        imageDescription= "Squirrel",
        text = "Hurra, der Park ist gerettet!\n" +
                "\n" +
                "Vielen Dank für deine Hilfe! Als kleines Dankeschön möchten wir dir natürlich auch etwas von unserem Fund abgeben. \n" +
                "\n" +
                "Mit diesem kannst du dir im Shop des Tierparks etwas Tolles aussuchen.",
        btnText = "Weiter",
        onBtnClick = {},
        onClose = {}
    )
}