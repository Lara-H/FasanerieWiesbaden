package de.hsrm.mi.mc.fasaneriewiesbaden.components

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel

@Composable
fun GoogleMaps(navController: NavHostController, data: MainActivityViewModel) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(data.stations[data.nextStationKey.value].mapLatitude, data.stations[data.nextStationKey.value].mapLongitude), 15f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 70.dp),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapType = MapType.HYBRID
        ),
        uiSettings = MapUiSettings(
            compassEnabled = false,
            mapToolbarEnabled = false,
        )
    ) {
        MapMarker(
            position = LatLng(data.currentLocation.value.latitude, data.currentLocation.value.longitude),
            title = stringResource(R.string.map_own_location),
            snippet = data.currentLocation.value.latitude.toString() + ", " + data.currentLocation.value.longitude.toString(),
            iconResourceId = R.drawable.marker_my_location
        )

        data.stations.forEach {
            if (it.isDone) {
                MapMarker(
                    position = LatLng(it.mapLatitude, it.mapLongitude),
                    title = it.locationName.asString(),
                    snippet = it.animalName.asString(),
                    iconResourceId = R.drawable.marker_location_done
                )
            } else if (it == data.stations[data.nextStationKey.value]) {
                MapMarker(
                    position = LatLng(it.mapLatitude, it.mapLongitude),
                    title = it.locationName.asString(),
                    snippet = it.animalName.asString(),
                    iconResourceId = R.drawable.marker_location_next
                )
            }
        }

        data.openNextStation(navController)
    }
}

@Composable
fun GoogleMaps(data: MainActivityViewModel) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(data.stations[data.nextStationKey.value].mapLatitude, data.stations[data.nextStationKey.value].mapLongitude), 18f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 70.dp),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapType = MapType.SATELLITE
        ),
        uiSettings = MapUiSettings(
            compassEnabled = false,
            rotationGesturesEnabled = false,
            scrollGesturesEnabled = false,
            scrollGesturesEnabledDuringRotateOrZoom = false,
            tiltGesturesEnabled = false,
            zoomControlsEnabled = false,
            zoomGesturesEnabled = false
        )
    )
}

@Composable
fun MapMarker(
    position: LatLng,
    title: String,
    snippet: String,
    @DrawableRes iconResourceId: Int
) {
    val context = LocalContext.current
    val icon = bitmapDescriptorFromVector(context, iconResourceId)

    Marker(
        state = MarkerState(position = position),
        title = title,
        snippet = snippet,
        icon = icon,
    )
}

fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}