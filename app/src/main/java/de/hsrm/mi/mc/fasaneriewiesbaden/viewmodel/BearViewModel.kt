package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import kotlin.math.roundToInt

class BearViewModel(val screenSize: ScreenSize, val gameBoxHeightPx: Int, val cupSizePx: Int): ViewModel() {
    val totalPoints = 5

    var isDone = mutableStateOf(false)
        private set
    var startGame = mutableStateOf(false)
        private set
    var onUpdate = mutableStateOf(0)
        private set
    var currentPoints by mutableStateOf(0)
        private set
    var drop = mutableStateOf(Item())
        private set
    var bee = mutableStateOf(Item())
        private set
    var cupPosition = mutableStateOf(0)
        private set

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    fun startGame() {
        startGame.value = true
        updateUI()
    }

    fun moveItems() {
        var newDrop = Item(offsetX = drop.value.offsetX, offsetY = drop.value.offsetY + 15)
        var newBee = Item(offsetX = bee.value.offsetX, offsetY = bee.value.offsetY + 15)

        if (newDrop.offsetY > gameBoxHeightPx) {
            newDrop = Item()
        }
        if (newBee.offsetY > gameBoxHeightPx) {
            newBee = Item()
        }
        drop = mutableStateOf(newDrop)
        bee = mutableStateOf(newBee)
        updateUI()
    }

    fun changeCupPosition(dragAmountX: Float) {
        var newCupPosition = cupPosition.value + dragAmountX.roundToInt()

        // Start
        if (newCupPosition < 1f) {
            newCupPosition = 0
        }
        // End
        if (newCupPosition > (screenSize.screenWidthPx-cupSizePx)) {
            newCupPosition = (screenSize.screenWidthPx-cupSizePx)
        }

        cupPosition = mutableStateOf(newCupPosition)
        updateUI()
    }

    fun checkIfCaught() {
        var distanceDrop = (cupPosition.value + cupSizePx/4) - drop.value.offsetX
        if (distanceDrop < 0) {
            distanceDrop *= (-1)
        }

        var distanceBee = (cupPosition.value + cupSizePx/4) - bee.value.offsetX
        if (distanceBee < 0) {
            distanceBee *= (-1)
        }

        if ((distanceDrop < cupSizePx/2) && (drop.value.offsetY > gameBoxHeightPx-(cupSizePx/2))) {
            addPoint()
            drop = mutableStateOf(Item())
        }

        if ((distanceBee < cupSizePx/2) && (bee.value.offsetY > gameBoxHeightPx-(cupSizePx/2))) {
            deletePoint()
            bee = mutableStateOf(Item())
        }
    }

    private fun addPoint() {
        currentPoints++
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
    }

    private fun deletePoint() {
        currentPoints--
    }

    inner class Item(val imgPath: Int = R.drawable.beehoney, var offsetX: Int = (0..screenSize.screenWidthPx-cupSizePx).random(), var offsetY: Int = (-screenSize.screenWidthPx..0).random())

}