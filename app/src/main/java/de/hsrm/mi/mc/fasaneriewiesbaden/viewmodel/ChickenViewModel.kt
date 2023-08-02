package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import de.hsrm.mi.mc.fasaneriewiesbaden.model.ScreenSize

class ChickenViewModel(val screenSize: ScreenSize, val offsetTop: Int, val offsetBottom: Int, val eggSizePx: Int, val chickenSizePx: Int): ViewModel() {
    val totalPoints = 5

    var isDone = mutableStateOf(false)
        private set
    var egg by mutableStateOf(Egg(imgPath = getRandomEggColor()))
        private set
    var currentPoints by mutableStateOf(0)
        private set

    fun changeEggPosition(dragAmountX: Float, dragAmountY: Float) {
        var newEgg = Egg(offsetX = egg.offsetX + dragAmountX, offsetY = egg.offsetY + dragAmountY, egg.imgPath)

        // Start
        if (newEgg.offsetX < 1f) {
            newEgg.offsetX = 0f
        }
        // End
        if (newEgg.offsetX > (screenSize.screenWidthPx-eggSizePx).toFloat()) {
            newEgg.offsetX = (screenSize.screenWidthPx-eggSizePx).toFloat()
        }
        // Top
        if (newEgg.offsetY <= offsetTop) {
            newEgg.offsetY = offsetTop.toFloat()
        }
        // Bottom
        if (newEgg.offsetY > (screenSize.screenHeightPx-offsetBottom).toFloat()) {
            newEgg.offsetY = (screenSize.screenHeightPx-offsetBottom).toFloat()
        }

        // onLeftChicken
        if (newEgg.offsetX < (screenSize.screenWidthPx/2) && newEgg.offsetY > screenSize.screenHeightPx-chickenSizePx) {
            if (newEgg.imgPath == R.drawable.egg_brown) {
                addPoint()
            }
            newEgg = Egg(imgPath = getRandomEggColor())
        }
        // onRightChicken
        if (newEgg.offsetX > (screenSize.screenWidthPx/2) && newEgg.offsetY > screenSize.screenHeightPx-chickenSizePx) {
            if (newEgg.imgPath == R.drawable.egg) {
                addPoint()
            }
            newEgg = Egg(imgPath = getRandomEggColor())
        }

        egg = newEgg
    }

    private fun getRandomEggColor(): Int {
        val random = (0..1).random()
        val imgPath = if (random == 0) {
            R.drawable.egg
        } else {
            R.drawable.egg_brown
        }
        return imgPath
    }

    private fun addPoint() {
        currentPoints++
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
    }

    inner class Egg(var offsetX: Float = (0 until screenSize.screenWidthPx-eggSizePx).random().toFloat(), var offsetY: Float = (offsetTop until screenSize.screenHeightPx-offsetBottom-chickenSizePx).random().toFloat(), val imgPath: Int)

}