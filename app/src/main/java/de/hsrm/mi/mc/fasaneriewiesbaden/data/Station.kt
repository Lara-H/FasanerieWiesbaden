package de.hsrm.mi.mc.fasaneriewiesbaden.data

import de.hsrm.mi.mc.fasaneriewiesbaden.graphs.Graph
import kotlinx.coroutines.flow.MutableStateFlow

class Station(
    val locationName: String,
    val animalName: String,
    val mapLatitude: Double,
    val mapLongitude: Double,
    var isDone: Boolean,
    val graphName: String,
) {
}

class Data() {
    var listStationsState =
        MutableStateFlow(listOf(
            Station("Eingang", "Elli Eichhorn",50.10296712995634, 8.19239066804138, false, Graph.INTRO),
            Station("Ziegen", "Björn Bock", 50.103323, 8.192826, false, Graph.GOAT),
            Station("Füchse", "Fiona Fuchs", 50.104116, 8.194161, false, Graph.FOX),
            Station("Bären", "Bruno Bär", 50.106387, 8.196074, false, Graph.BEAR),
            Station("Luchse", "Ludwig Luchs", 50.106107, 8.194052, false, Graph.LYNX),
            Station("Hirsche", "Hugo Hirsch",50.105601, 8.192938, false, Graph.DEER),
            Station("Waschbären", "Wilma Waschbär", 50.105282, 8.193426, false, Graph.RACCOON),
            Station("Greifvögel", "Emilia Eule", 50.104350, 8.192898, false, Graph.OWL),
            Station("Teichvögel", "Henry Hahn", 50.104055, 8.190708, false, Graph.CHICKEN),
            Station("Fischotter", "Olli Otter",50.103602, 8.191567, false, Graph.OTTER),
            Station("Fledermäuse", "Frieda Fledermaus",50.102673, 8.191353, false, Graph.BAT)
        ))
    var currentStationState = MutableStateFlow(listStationsState.value[0])
}