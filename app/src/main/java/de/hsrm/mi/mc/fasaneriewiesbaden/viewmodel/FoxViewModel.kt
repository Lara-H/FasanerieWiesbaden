package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import java.util.Timer
import kotlin.concurrent.schedule

class FoxViewModel(val screenWidthPx: Int, val imgSizePx: Int): ViewModel() {
    val holeImgPath = R.drawable.fox_hole
    val holeImgAltText = "Hole"

    val dustImgPath = R.drawable.fox_dust
    val dustImgAltText = "Dust"

    val handsImgPath = R.drawable.hands
    val handsImgAltText = "Hands"
    val handsImgSize = 100.dp

    val itemImgPath = R.drawable.fox_bug
    val itemImgAltText = "Bug"
    val itemImgSize = 30.dp

    val totalPoints = 5

    var isDone = mutableStateOf(false)
        private set
    var currentPoints by mutableStateOf(0)
        private set
    var hands = mutableStateOf(Hands(0f, 0f))
        private set
    var oldroffsetX = mutableStateOf(0f)
        private set
    var distance = mutableStateOf(0f)
        private set
    var isItemVisible = mutableStateOf(0f)
        private set
    var dustVisible = mutableStateOf(0f)
        private set
    var holeVisible = mutableStateOf(0f)
        private set

    fun changeHandsPosition(dragAmountX: Float, dragAmountY: Float) {
        var newHands = Hands(offsetX = hands.value.offsetX + dragAmountX, offsetY = hands.value.offsetY + dragAmountY)

        dustVisible.value = .25f

        // Start
        if (newHands.offsetX < ((screenWidthPx/2)-(imgSizePx/2))*(-1)) {
            newHands.offsetX = ((screenWidthPx/2)-(imgSizePx/2))*(-1).toFloat()
        }
        // End
        if (newHands.offsetX > (screenWidthPx/2)-(imgSizePx/2)) {
            newHands.offsetX = (screenWidthPx/2)-(imgSizePx/2).toFloat()
        }
        // Top
        if (newHands.offsetY < imgSizePx*(-1)) {
            newHands.offsetY = imgSizePx*(-1).toFloat()
        }
        // Bottom
        if (newHands.offsetY > imgSizePx) {
            newHands.offsetY = imgSizePx.toFloat()
        }

        distance.value = oldroffsetX.value - hands.value.offsetX
        if (distance.value < 0) {
            distance.value *= (-1)
        }

        if (distance.value > imgSizePx) {
            if (isItemVisible.value == 0f) {
                val random = (0..5).shuffled().last()
                if (random == 0) {
                    isItemVisible.value = 1f
                    addPoint()
                    Timer().schedule(5000){
                        isItemVisible.value = 0f
                    }
                }
            }
            oldroffsetX.value = hands.value.offsetX
        }

        Timer().schedule(2000){
            dustVisible.value = 0f
        }

        hands.value = newHands
    }

    fun addPoint() {
        currentPoints++
        holeVisible.value += (1f / totalPoints.toFloat())
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
    }

    inner class Hands(var offsetX: Float, var offsetY: Float)
}