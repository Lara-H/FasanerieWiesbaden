package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import de.hsrm.mi.mc.fasaneriewiesbaden.LocationDetails
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.UiText
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import java.util.UUID

class MainActivityViewModel(): ViewModel() {

    val userID: UUID = UUID.randomUUID()
    var currentLocation = mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
        private set
    var nextStationKey = mutableStateOf(0)
        private set
    var nextStationButtonVisible = mutableStateOf(false)
        private set

    var stations = mutableStateListOf(
        Station(UiText.StringResource(resId = R.string.title_location_squirrel), UiText.StringResource(resId = R.string.title_name_squirrel),50.10296712995634, 8.19239066804138, Graph.INTRO),
        Station(UiText.StringResource(resId = R.string.title_location_goat), UiText.StringResource(resId = R.string.title_name_goat), 50.103323, 8.192826, Graph.GOAT),
        Station(UiText.StringResource(resId = R.string.title_location_fox), UiText.StringResource(resId = R.string.title_name_fox), 50.104116, 8.194161, Graph.FOX),
        Station(UiText.StringResource(resId = R.string.title_location_bear), UiText.StringResource(resId = R.string.title_name_bear), 50.106387, 8.196074, Graph.BEAR),
        Station(UiText.StringResource(resId = R.string.title_location_lynx), UiText.StringResource(resId = R.string.title_name_lynx), 50.106107, 8.194052, Graph.LYNX),
        Station(UiText.StringResource(resId = R.string.title_location_deer), UiText.StringResource(resId = R.string.title_name_deer),50.105601, 8.192938, Graph.DEER),
        Station(UiText.StringResource(resId = R.string.title_location_raccoon), UiText.StringResource(resId = R.string.title_name_raccoon), 50.105282, 8.193426, Graph.RACCOON),
        Station(UiText.StringResource(resId = R.string.title_location_chicken), UiText.StringResource(resId = R.string.title_name_chicken), 50.104055, 8.190708, Graph.CHICKEN),
        Station(UiText.StringResource(resId = R.string.title_location_otter), UiText.StringResource(resId = R.string.title_name_otter),50.103602, 8.191567, Graph.OTTER),
        Station(UiText.StringResource(resId = R.string.title_location_bat), UiText.StringResource(resId = R.string.title_name_bat),50.102673, 8.191353, Graph.BAT)
    )
        private set

    private fun checkIfNextStationInNear(currentLocation: LocationDetails): Boolean {
        val distanceRadius = 0.000005

        var distanceLatitude = currentLocation.latitude - stations[nextStationKey.value].mapLatitude
        if (distanceLatitude < 0) {
            distanceLatitude * (-1)
        }

        var distanceLongitude = currentLocation.longitude - stations[nextStationKey.value].mapLongitude
        if (distanceLongitude < 0) {
            distanceLongitude * (-1)
        }

        if (distanceLatitude < distanceRadius && distanceLongitude < distanceRadius) {
            return true
        }
        return false

    }

    fun openNextStation(navController: NavHostController) {
        if (checkIfNextStationInNear(currentLocation.value)) {
            if (!stations[nextStationKey.value].wasAlreadyOpened) {
                stations[nextStationKey.value].wasAlreadyOpened = true
                navController.navigate(stations[nextStationKey.value].graph)
            } else {
                nextStationButtonVisible = mutableStateOf(true)
            }
        } else {
            nextStationButtonVisible = mutableStateOf(false)
        }
    }

    fun stationDone() {
        stations[nextStationKey.value].isDone = true
        nextStationKey.value++
    }

    fun updateCurrentLocation(newLocation: LocationDetails) {
        currentLocation = mutableStateOf(newLocation)
    }

    inner class Station(
        val locationName: UiText,
        val animalName: UiText,
        val mapLatitude: Double,
        val mapLongitude: Double,
        val graph: String,
        var wasAlreadyOpened: Boolean = false,
        var isDone: Boolean = false,
    )
}