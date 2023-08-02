package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize
import java.util.Timer
import kotlin.concurrent.schedule

class FoxViewModel(val screenSize: ScreenSize, val handsImgSizePx: Int): ViewModel() {
    val totalPoints = 5

    var isDone = mutableStateOf(false)
        private set
    var currentPoints by mutableStateOf(0)
        private set
    var hands = mutableStateOf(Hands(0f, 0f))
        private set
    var bugVisible = mutableStateOf(0f)
        private set
    var dustVisible = mutableStateOf(0f)
        private set
    var holeVisible = mutableStateOf(0f)
        private set

    private var oldOffsetX = mutableStateOf(0f)

    fun changeHandsPosition(dragAmountX: Float, dragAmountY: Float) {
        val newHands = Hands(offsetX = hands.value.offsetX + dragAmountX, offsetY = hands.value.offsetY + dragAmountY)

        dustVisible.value = .25f

        // Start
        if (newHands.offsetX < ((screenSize.screenWidthPx/2)-(handsImgSizePx/2))*(-1)) {
            newHands.offsetX = ((screenSize.screenWidthPx/2)-(handsImgSizePx/2))*(-1).toFloat()
        }
        // End
        if (newHands.offsetX > (screenSize.screenWidthPx/2)-(handsImgSizePx/2)) {
            newHands.offsetX = (screenSize.screenWidthPx/2)-(handsImgSizePx/2).toFloat()
        }
        // Top
        if (newHands.offsetY < handsImgSizePx*(-1)) {
            newHands.offsetY = handsImgSizePx*(-1).toFloat()
        }
        // Bottom
        if (newHands.offsetY > handsImgSizePx) {
            newHands.offsetY = handsImgSizePx.toFloat()
        }

        // Distance after moving hands
        var distance = oldOffsetX.value - hands.value.offsetX
        if (distance < 0) {
            distance *= (-1)
        }

        // Show Bug
        if (distance > handsImgSizePx) {
            if (bugVisible.value == 0f) {
                val random = (0..5).random()
                if (random == 0) {
                    bugVisible.value = 1f
                    addPoint()
                    Timer().schedule(5000){
                        bugVisible.value = 0f
                    }
                }
            }
            oldOffsetX.value = hands.value.offsetX
        }

        Timer().schedule(2000){
            dustVisible.value = 0f
        }

        hands.value = newHands
    }

    private fun addPoint() {
        currentPoints++
        holeVisible.value += (1f / totalPoints.toFloat())
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
    }

    inner class Hands(var offsetX: Float, var offsetY: Float)
}