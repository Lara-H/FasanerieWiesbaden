package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MapScreen(navController: NavHostController, data: Data) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(data.currentStationState.value.mapLatitude, data.currentStationState.value.mapLongitude), 20f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize() .padding(top = 64.dp, bottom = 82.dp),
        cameraPositionState = cameraPositionState,
        ) {
        data.listStationsState.value.forEach {
            if (it.isDone || it == data.currentStationState.value) {
                Marker(
                    state = MarkerState(position = LatLng(it.mapLatitude, it.mapLongitude)),
                    title = it.locationName,
                    snippet = it.animalName,
                    icon = if (data.currentStationState.value == it) {
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    } else {
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    }
                )
            }
        }
    }

    TextBox("Bewege dich von deinem Standort aus zum grünen Punkt auf der Karte", Color.White, Color.Black)
}