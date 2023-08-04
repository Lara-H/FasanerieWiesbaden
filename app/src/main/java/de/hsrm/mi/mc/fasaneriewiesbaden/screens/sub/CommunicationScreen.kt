package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.colorpalette
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunicationScreen(data: MainActivityViewModel, title: String? = null, imagePath: Int? = null, text: String, btnText: String? = null, onBtnClick: () -> Unit, onClose: () -> Unit) {

    val transition = rememberInfiniteTransition()
    val offsetCloud by transition.animateFloat(
        initialValue = -200f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorpalette.blue_500),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(text=createTitle(data, title), onClose = onClose)
        Column {
            Box(modifier = Modifier .fillMaxWidth()) {
                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .offset(x = offsetCloud.toInt().dp),
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = "Cloud",
                    alignment = Alignment.TopCenter,
                )
                Image(
                    painter = painterResource(R.drawable.tree),
                    contentDescription = "Tree",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = MaterialTheme.spacing.small),
                )
                Image(
                    painter = painterResource(id = createImgPath(data, imagePath)),
                    contentDescription = createTitle(data, title),
                    modifier = Modifier .align(Alignment.BottomEnd)
                )
            }
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
}


@Composable
fun createTitle(data: MainActivityViewModel, title: String?): String {
    val newTitle = data.stations[data.nextStationKey.value].animalName.asString()
    if (title != null) {
        return title
    }
    return newTitle
}

@Composable
fun createImgPath(data: MainActivityViewModel, imagePath: Int?): Int {
    val newImgPath = data.stations[data.nextStationKey.value].imgPath
    if (imagePath != null) {
        return imagePath
    }
    return newImgPath
}

@Composable
fun createBtnText(btnText: String?): String {
    val newBtnText = stringResource(R.string.communication_btn)
    if (btnText != null) {
        return btnText
    }
    return newBtnText
}

@Preview(showBackground = true)
@Composable
fun CommunicationScreenPreview() {
    CommunicationScreen(
        data = MainActivityViewModel(screenSize = ScreenSize(0.dp, 0.dp, 0, 0)),
        text = "Lorem ipsum dolor sit amet",
        onBtnClick = {},
        onClose = {},
    )
}