package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import kotlinx.coroutines.delay
import java.util.Timer
import kotlin.concurrent.schedule

class BearViewModel(val screenHeight: Dp, val screenWidth: Dp, val screenHeightPx: Int, val screenWidthPx: Int, val cupSizePx: Int): ViewModel() {

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
    var cupPosition = mutableStateOf(0f)
        private set

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    fun startGame() {
        startGame.value = true
        updateUI()
    }

    fun drop() {
        var newDrop = Item(offsetX = drop.value.offsetX, offsetY = drop.value.offsetY + 15)
        var newBee = Item(offsetX = bee.value.offsetX, offsetY = bee.value.offsetY + 15)

        if (newDrop.offsetY > screenHeightPx) {
            newDrop = Item()
        }
        if (newBee.offsetY > screenHeightPx) {
            newBee = Item()
        }
        drop = mutableStateOf(newDrop)
        bee = mutableStateOf(newBee)
        updateUI()
    }

    fun changeCupPosition(dragAmountX: Float, dragAmountY: Float) {
        var newCupPosition = cupPosition.value + dragAmountX
        // Start
        if (newCupPosition < 1f) {
            newCupPosition = 0f
        }
        // End
        if (newCupPosition > (screenWidthPx-cupSizePx).toFloat()) {
            newCupPosition = (screenWidthPx-cupSizePx).toFloat()
        }
        cupPosition = mutableStateOf(newCupPosition)
        updateUI()
    }

    fun checkIfCatched() {
        var distanceDrop = cupPosition.value - drop.value.offsetX
        if (distanceDrop < 0) {
            distanceDrop *= (-1)
        }

        var distanceBee = cupPosition.value - bee.value.offsetX
        if (distanceBee < 0) {
            distanceBee *= (-1)
        }

        if ((distanceDrop < cupSizePx) && (drop.value.offsetY > screenHeightPx-(cupSizePx/2))) {
            addPoint()
            drop = mutableStateOf(Item())
        }
        if ((distanceBee < cupSizePx) && (bee.value.offsetY > screenHeightPx-(cupSizePx/2))) {
            deletePoint()
            bee = mutableStateOf(Item())
        }
    }

    fun addPoint() {
        currentPoints++
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
    }

    fun deletePoint() {
        currentPoints--
    }

    inner class Item(val imgPath: Int = R.drawable.beehoney, var offsetX: Int = (0..screenWidthPx-cupSizePx).random(), var offsetY: Int = (-screenWidthPx..0).random())

}