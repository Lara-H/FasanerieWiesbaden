package de.hsrm.mi.mc.fasaneriewiesbaden.screens.sub

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CodeScreen(url: String) {
    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.title_code), isMainNav = false) },
    ) {
    }
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
    // Make sure the value is valid for the type of barcode selected. The library will
    // just show an infinite spinner in place of a barcode if the data is not valid.
        if (BarcodeType.QR_CODE.isValueValid(url)) {
            Barcode(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(250.dp)
                    .height(250.dp),
                resolutionFactor = 10, // Optionally, increase the resolution of the generated image
                type = BarcodeType.QR_CODE, // pick the type of barcode you want to render
                value = url // The textual representation of this code
            )
        }
    // You must handle invalid data yourself
        if (!BarcodeType.CODE_128.isValueValid(url)) {
            Text("this is not code 128 compatible")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CodeScreenPreview() {
    CodeScreen(url = "https://fasanerie.net/")
}