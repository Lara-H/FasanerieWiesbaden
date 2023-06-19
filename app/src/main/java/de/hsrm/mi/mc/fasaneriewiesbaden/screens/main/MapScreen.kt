package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import de.hsrm.mi.mc.fasaneriewiesbaden.data.Data

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MapScreen(onBtnClick: () -> Unit, data: Data) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(data.nextStationState.value.mapLatitude, data.nextStationState.value.mapLongitude), 20f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize() .padding(top = 64.dp, bottom = 82.dp),
        cameraPositionState = cameraPositionState,
        ) {
        data.listStationsState.value.stations.forEach {
            if (it.isDone || it == data.nextStationState.value) {
                Marker(
                    state = MarkerState(position = LatLng(it.mapLatitude, it.mapLongitude)),
                    title = it.name,
                    snippet = "Start",
                    onInfoWindowClick = { onBtnClick() },
                    icon = if (data.nextStationState.value == it) {
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    } else {
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    }
                )
            }
        }
    }
}