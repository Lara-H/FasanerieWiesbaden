package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R

class FoxViewModel(): ViewModel() {
    val holeImgPath = R.drawable.hole
    val holeImgAltText = "Hole"

    val handsImgPath = R.drawable.hands
    val handsImgAltText = "Hands"
    val handsImgSize = 100.dp

    val itemImgPath = R.drawable.money
    val itemImgAltText = "Item"
    val itemImgSize = 30.dp

    val totalPoints = 5

    var isDone = mutableStateOf(false)
        private set
    var onUpdate = mutableStateOf(0)
        private set
    var currentPoints by mutableStateOf(0)
        private set
    var hands = mutableStateOf(Hands(0f, 0f))
        private set
    var item = mutableStateOf(Item(0f, 0f))
        private set
    var distance = mutableStateOf(0f)
        private set
    var isItemVisible = mutableStateOf(false)
        private set

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    fun moveHands() {

    }

    fun addPoint() {
        currentPoints++
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
    }

    inner class Hands(var offsetX: Float, offsetY: Float)
    inner class Item(var offsetX: Float, offsetY: Float)
}