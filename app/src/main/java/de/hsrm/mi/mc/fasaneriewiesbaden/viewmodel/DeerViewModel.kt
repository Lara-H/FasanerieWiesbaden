package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.UiText
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.MutableStateFlow
import mu.KotlinLogging

class DeerViewModel(private val onDone: () -> Unit, appContext: Context): ViewModel() {
    private val logger = KotlinLogging.logger {}

    // TODO: In a final app this would come from a server which is connected to automatic feeder
    private val correctCodeResult = "t3st_qr_c0d3_1d_123"

    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_ALL_FORMATS
        )
        .build()

    private val scanner = GmsBarcodeScanning.getClient(appContext, options)
    val barCodeResults = MutableStateFlow<String?>(null)

    suspend fun startScan() {
        try {
            val result = scanner.startScan().await()

            if (result.rawValue == correctCodeResult) {
                onDone()
            } else {
                barCodeResults.value = UiText.StringResource(resId = R.string.station_deer_game_error).toString()
            }
            logger.info(barCodeResults.value)
        } catch (e: Exception) {
            logger.error("scan error: $e")
        }
    }

}