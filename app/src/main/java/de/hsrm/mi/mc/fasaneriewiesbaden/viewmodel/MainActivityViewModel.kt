package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.components.UiText
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import de.hsrm.mi.mc.fasaneriewiesbaden.model.LocationDetails
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import java.util.UUID

class MainActivityViewModel(val screenSize: ScreenSize): ViewModel() {

    var userID: UUID = UUID.randomUUID()
    var gameDone = mutableStateOf(false)
        private set
    var currentLocation = mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
        private set
    var nextStationKey = mutableStateOf(0)
        private set
    var nextStationButtonVisible = mutableStateOf(false)
        private set

    var stations = mutableStateListOf(
        Station(UiText.StringResource(resId = R.string.title_location_squirrel), UiText.StringResource(resId = R.string.title_name_squirrel),R.drawable.squirrel, 50.10296712995634, 8.19239066804138, Graph.INTRO),

        //Station(UiText.StringResource(resId = R.string.title_location_goat), UiText.StringResource(resId = R.string.title_name_goat), R.drawable.goat, 50.103323, 8.192826, Graph.GOAT),
        Station(UiText.StringResource(resId = R.string.title_location_goat), UiText.StringResource(resId = R.string.title_name_goat), R.drawable.goat, 50.0457609, 8.2426477, Graph.GOAT),

        Station(UiText.StringResource(resId = R.string.title_location_fox), UiText.StringResource(resId = R.string.title_name_fox), R.drawable.fox, 50.104116, 8.194161, Graph.FOX),
        Station(UiText.StringResource(resId = R.string.title_location_bear), UiText.StringResource(resId = R.string.title_name_bear), R.drawable.bear, 50.106387, 8.196074, Graph.BEAR),
        Station(UiText.StringResource(resId = R.string.title_location_lynx), UiText.StringResource(resId = R.string.title_name_lynx), R.drawable.lynx, 50.106107, 8.194052, Graph.LYNX),
        Station(UiText.StringResource(resId = R.string.title_location_deer), UiText.StringResource(resId = R.string.title_name_deer), R.drawable.deer, 50.105601, 8.192938, Graph.DEER),
        Station(UiText.StringResource(resId = R.string.title_location_raccoon), UiText.StringResource(resId = R.string.title_name_raccoon), R.drawable.raccoon, 50.105282, 8.193426, Graph.RACCOON),
        Station(UiText.StringResource(resId = R.string.title_location_chicken), UiText.StringResource(resId = R.string.title_name_chicken), R.drawable.chicken, 50.104055, 8.190708, Graph.CHICKEN),
        Station(UiText.StringResource(resId = R.string.title_location_otter), UiText.StringResource(resId = R.string.title_name_otter), R.drawable.otter, 50.103602, 8.191567, Graph.OTTER),
        Station(UiText.StringResource(resId = R.string.title_location_bat), UiText.StringResource(resId = R.string.title_name_bat), R.drawable.bat, 50.102673, 8.191353, Graph.BAT)
    )
        private set

    fun updateCurrentLocation(newLocation: LocationDetails) {
        currentLocation = mutableStateOf(newLocation)
    }

    private fun checkIfNextStationInNear(currentLocation: LocationDetails): Boolean {
        val distanceRadius = 0.0002

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
        if (gameDone.value) {
            nextStationButtonVisible = mutableStateOf(true)
        } else {
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
    }

    fun stationDone() {
        stations[nextStationKey.value].isDone = true
        if (nextStationKey.value < stations.size-1) {
            nextStationKey.value++
        } else {
            gameDone.value = true
        }
    }

    fun resetGame() {
        userID = UUID.randomUUID()
        gameDone.value = false
        nextStationKey.value = 0
        stations.forEach() {
            it.wasAlreadyOpened = false
            it.isDone = false
        }
    }

    inner class Station(
        val locationName: UiText,
        val animalName: UiText,
        val imgPath: Int,
        val mapLatitude: Double,
        val mapLongitude: Double,
        val graph: String,
        var wasAlreadyOpened: Boolean = false,
        var isDone: Boolean = false,
    )
}