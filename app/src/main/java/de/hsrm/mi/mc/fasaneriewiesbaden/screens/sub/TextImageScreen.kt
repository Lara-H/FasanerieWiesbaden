package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TextImageScreen(title: String, imagePath: Int, imageDescription: String, text: String, btnText: String, onBtnClick: () -> Unit) {
    Scaffold(
        topBar = { TopBar(text=title, isMainNav = false) },
    ) {
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(all = MaterialTheme.spacing.medium),
            text = text,
        )
        Image(painter = painterResource(
            id = imagePath),
            contentDescription = imageDescription,
            modifier = Modifier
                .align(Alignment.End)
        )
        BottomButton(
            onClick = { onBtnClick() },
            text = btnText,
        )
    }
}

@Preview
@Composable
fun TextImageScreenPreview() {
    TextImageScreen(
        title = "Fledermaushöhle",
        imagePath = R.drawable.treasure,
        imageDescription = "Treasure",
        text = "Gemeinsam gelingt es den Tieren den Schatz zu bergen…",
        btnText = "Weiter",
        onBtnClick = {}
    )
}