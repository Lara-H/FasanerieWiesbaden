package de.hsrm.mi.mc.fasaneriewiesbaden

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.FasanerieWiesbadenTheme
import android.Manifest
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.*
import com.google.android.libraries.places.api.Places
import de.hsrm.mi.mc.fasaneriewiesbaden.components.LockScreenOrientation
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.RootNavGraph
import de.hsrm.mi.mc.fasaneriewiesbaden.model.LocationDetails
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.sizing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {
    private var locationCallback: LocationCallback? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequired = false

    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1234
    }

    private val locationPermissionGranted = mutableStateOf(false)

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Places.initialize(applicationContext, "AIzaSyD0TDOq1EBmtZlMDbu_nlOvOYGlLZmK5PM")

        setContent {
            FasanerieWiesbadenTheme {
                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                hidePhoneBars()
                val screenSize = getScreenSize()

                // viewModel
                val viewModel = viewModel<MainActivityViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return MainActivityViewModel(screenSize) as T
                        }
                    }
                )

                // detect any changes to data and recompose composable
                viewModel.onUpdate.value

                // Get current location
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        for (lo in p0.locations) {
                            // Update UI with location data
                            viewModel.updateCurrentLocation(LocationDetails(lo.latitude, lo.longitude))
                        }
                    }
                }
                // Check for permission before showing anything
                getLocationPermission()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    if (locationPermissionGranted.value) {
                        locationRequired = true
                        startLocationUpdates()
                        RootNavGraph(rememberNavController(), viewModel)
                    }else{
                        Box(modifier = Modifier.padding(top = MaterialTheme.sizing.topBar)) {
                            TextBox(stringResource(R.string.map_text), colorText = Color.Red)
                        }
                    }
                }
            }
        }

    }

    private fun hidePhoneBars() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.navigationBars())
            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    @Composable
    private fun getScreenSize(): ScreenSize {
        val density = LocalDensity.current
        val configuration = LocalConfiguration.current
        return ScreenSize(
            screenHeight = configuration.screenHeightDp.dp,
            screenWidth = configuration.screenWidthDp.dp,
            screenHeightPx = with(density) { configuration.screenHeightDp.dp.roundToPx() },
            screenWidthPx = with(density) { configuration.screenWidthDp.dp.roundToPx() },
        )
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted.value = true //we already have the permission
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            locationPermissionGranted.value=true
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationCallback?.let {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            fusedLocationClient?.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        WindowCompat.getInsetsController(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        if (locationRequired) {
            startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        locationCallback?.let { fusedLocationClient?.removeLocationUpdates(it) }
    }

}