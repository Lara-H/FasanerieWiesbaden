package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.MutableStateFlow

class DeerViewModel(private val onDone: () -> Unit, appContext: Context): ViewModel() {

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
                // https://fasanerie.net/
                onDone()
            } else {
                barCodeResults.value = "Bitte versuche es erneut, das scheint der falsche Code gewesen zu sein."
            }
            //TODO() Logger importieren
            //Timber.d(barCodeResults.value)
        } catch (e: Exception) {
            //Timber.d("scan error: $e")
        }
    }

}