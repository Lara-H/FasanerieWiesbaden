package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class OtterViewModel(val screenWidth: Dp): ViewModel() {

    var fish by mutableStateOf(Fish(0.dp, 0.dp))
        private set

    var currentPoints by mutableStateOf(0)
        private set

    fun moveFish() {
        val newFish = Fish(fish.offsetX + 10.dp, fish.offsetY)
        fish = newFish

        if (fish.offsetX > screenWidth) {
            fish = Fish((-150).dp, ((-150)..150).shuffled().last().dp)
        }

    }

    fun addPoint() {
        currentPoints++
    }

}

class Fish(var offsetX: Dp, var offsetY: Dp) {
}