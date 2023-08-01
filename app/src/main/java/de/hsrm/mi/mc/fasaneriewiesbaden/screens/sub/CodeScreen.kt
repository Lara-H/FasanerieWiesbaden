package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CodeScreen(onClose: () -> Unit, id: String) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(text = stringResource(R.string.title_code), onClose = onClose)

        if (BarcodeType.QR_CODE.isValueValid(id)) {
            Barcode(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(250.dp)
                    .height(250.dp),
                resolutionFactor = 10, // Optionally, increase the resolution of the generated image
                type = BarcodeType.QR_CODE, // pick the type of barcode you want to render
                value = id // The textual representation of this code
            )
        }
        if (!BarcodeType.CODE_128.isValueValid(id)) {
            Text(stringResource(R.string.outro_code_error), color = Color.Red)
        }

        Text(text = stringResource(R.string.outro_code_text), Modifier.padding(all = MaterialTheme.spacing.medium), color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(showBackground = true)
@Composable
fun CodeScreenPreview() {
    CodeScreen(
        onClose = {},
        id = "12345"
    )
}