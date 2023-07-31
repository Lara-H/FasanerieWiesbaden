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
import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import java.util.UUID

class MainActivityViewModel(): ViewModel() {

    val userID: UUID = UUID.randomUUID()

    var nextStationKey = mutableStateOf(0)
        private set

    var nextStationButtonVisible = mutableStateOf(false)
        private set

    var stations = mutableStateListOf(
        //Station("Eingang", 50.3642792, 8.160885, Graph.INTRO),
        //Station("Ziegengehege", 50.3644525,8.1612079, Graph.GOAT),
        Station("Eingang",50.10296712995634, 8.19239066804138, Graph.INTRO),
        Station("Ziegen", 50.103323, 8.192826, Graph.GOAT),
        Station("Füchse", 50.104116, 8.194161, Graph.FOX),
        Station("Bären", 50.106387, 8.196074, Graph.BEAR),
        Station("Luchse", 50.106107, 8.194052, Graph.LYNX),
        Station("Hirsche",50.105601, 8.192938, Graph.DEER),
        Station("Waschbären", 50.105282, 8.193426, Graph.RACCOON),
        Station("Teichvögel", 50.104055, 8.190708, Graph.CHICKEN),
        Station("Fischotter",50.103602, 8.191567, Graph.OTTER),
        Station("Fledermäuse",50.102673, 8.191353, Graph.BAT)
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

    fun openNextStation(currentLocation: LocationDetails, navController: NavHostController) {
        if (checkIfNextStationInNear(currentLocation)) {
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

    inner class Station(
        val locationName: String,
        val mapLatitude: Double,
        val mapLongitude: Double,
        val graph: String,
        var wasAlreadyOpened: Boolean = false,
        var isDone: Boolean = false,
    )
}