package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import androidx.compose.ui.unit.dp
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun IntroScreen(title: String, imagePath: Int, imageDescription: String, text: String, btnText: String) {

    Scaffold(
        topBar = { TopBar(text=title, isMainNav = false) },
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
                .background(color = Color.Cyan)
                .padding(all = MaterialTheme.spacing.medium)
                .height(200.dp)
                .verticalScroll(scroll),
            text = text,
        )
        BottomButton(text = btnText)
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    IntroScreen(
        title = "Elli Eichhorn",
        imagePath = R.drawable.squirrel,
        imageDescription= "Squirrel",
        text = "Hallo, mein Name ist Elli Eichhorn und ich bewache diesen Park.\n" +
            "\n" +
            "Aber der Park ist in Gefahr! Wenn wir nicht bald genug Geld auftreiben muss der Park verkauft werden und alle Tiere müssen sich ein neues Zuhause suchen.\n" +
            "\n" +
            "Jetzt kann uns nur noch der Goldschatz retten, der laut einer alten Legende im Park versteckt ist. Hilfst du uns ihn zu finden?",
        btnText = "Ja, ich helfe gerne!"
    )
}