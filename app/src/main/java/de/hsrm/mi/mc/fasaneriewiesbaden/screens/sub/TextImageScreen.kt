package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.BottomButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TextImageScreen(title: String, imagePath: Int, imageDescription: String, text: String, btnText: String, onClose: () -> Unit, onBtnClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.onBackground),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TopBar(text=title, onClose = onClose)
            TextBox(text = text, colorText = Color.White)
        }
        Image(
            painter = painterResource(id = imagePath),
            contentDescription = imageDescription,
            modifier = Modifier .padding(MaterialTheme.spacing.medium)
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
        title = "Titel",
        imagePath = R.drawable.treasure,
        imageDescription = "Bild",
        text = "Lorem ipsum dolor sit amet",
        btnText = "Lorem ipsum",
        onClose = {},
        onBtnClick = {}
    )
}