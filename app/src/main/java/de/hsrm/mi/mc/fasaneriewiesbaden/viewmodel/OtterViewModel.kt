package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize

class OtterViewModel(val screenSize: ScreenSize): ViewModel() {
    val fishImgSize = 150
    val totalPoints = 5

    var isDone = mutableStateOf(false)
        private set
    var onUpdate = mutableStateOf(0)
        private set
    var fishes = mutableStateListOf(Fish(), (Fish()), (Fish()))
        private set
    var currentPoints by mutableStateOf(0)
        private set

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    fun moveFishes() {
        val newFishes = mutableStateListOf<Fish>()
        fishes.forEach {
            var newFish = Fish(it.offsetX + 10.dp, it.offsetY, it.speed)
            if (it.offsetX > screenSize.screenWidth) {
                newFish = Fish()
            }
            newFishes.add(newFish)
        }
        fishes = newFishes
        updateUI()
    }

    fun addPoint() {
        currentPoints++
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
    }

    inner class Fish(var offsetX: Dp = (((fishImgSize*3)*(-1))..(fishImgSize*(-1))).random().dp, var offsetY: Dp = ((-50)..50).random().dp, var speed: Dp = (0..10).random().dp)
}