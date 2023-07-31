package de.hsrm.mi.mc.fasaneriewiesbaden.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.applyCanvas
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
import de.hsrm.mi.mc.fasaneriewiesbaden.LocationDetails
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.PrimaryButton
import de.hsrm.mi.mc.fasaneriewiesbaden.components.TextBox
import de.hsrm.mi.mc.fasaneriewiesbaden.ui.theme.spacing
import de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel.MainActivityViewModel
import java.io.File

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MapScreen(navController: NavHostController, data: MainActivityViewModel, currentLocation: LocationDetails) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(data.stations[data.nextStationKey.value].mapLatitude, data.stations[data.nextStationKey.value].mapLongitude), 18f)
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
            position = LatLng(currentLocation.latitude, currentLocation.longitude),
            title = "Dein Standort",
            iconResourceId = R.drawable.marker_my_location
        )

        data.stations.forEach {
            if (it.isDone) {
                MapMarker(
                    position = LatLng(it.mapLatitude, it.mapLongitude),
                    title = it.locationName,
                    iconResourceId = R.drawable.marker_location_done
                )
            } else if (it == data.stations[data.nextStationKey.value]) {
                MapMarker(
                    position = LatLng(it.mapLatitude, it.mapLongitude),
                    title = it.locationName,
                    iconResourceId = R.drawable.marker_location_next
                )
            }
        }

        data.openNextStation(currentLocation, navController)
    }

    Column {
        TextBox("Bewege dich von deinem Standort aus zum grünen Punkt auf der Karte", Color.White, Color(0x99000000))
        //if (data.nextStationButtonVisible.value) {
        Box(modifier = Modifier .padding(MaterialTheme.spacing.medium)) {
            PrimaryButton(text = "Öffnen", onClick = { navController.navigate(data.stations[data.nextStationKey.value].graph) })
        }
        //}
    }

}

@Composable
fun MapMarker(
    position: LatLng,
    title: String,
    @DrawableRes iconResourceId: Int
) {
    val context = LocalContext.current
    val icon = bitmapDescriptorFromVector(context, iconResourceId)

    Marker(
        state = MarkerState(position = position),
        title = title,
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