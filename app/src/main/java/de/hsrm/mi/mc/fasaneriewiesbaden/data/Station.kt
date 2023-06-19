package de.hsrm.mi.mc.fasaneriewiesbaden.data

import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import kotlinx.coroutines.flow.MutableStateFlow

data class StationState(val stations: List<Station>)

class Station(
    val name: String,
    val mapLatitude: Double,
    val mapLongitude: Double,
    var isDone: Boolean
) {
}

class Data() {
    val listStationsState =
        MutableStateFlow(StationState(stations = listOf(
            Station("Eingang", 50.10296712995634, 8.19239066804138, false),
            Station("Ziegen", 50.103323, 8.192826, false),
            Station("Füchse", 50.104116, 8.194161, false),
            Station("Bären", 50.106387, 8.196074, false),
            Station("Luchse", 50.106107, 8.194052, false),
            Station("Hirsche", 50.105601, 8.192938, false),
            Station("Waschbären", 50.105282, 8.193426, false),
            Station("Greifvögel", 50.104350, 8.192898, false),
            Station("Teichvögel", 50.104055, 8.190708, false),
            Station("Fischotter", 50.103602, 8.191567, false),
            Station("Fledermäuse", 50.102673, 8.191353, false)
        )))
    val nextStationState = MutableStateFlow(listStationsState.value.stations[0])
}