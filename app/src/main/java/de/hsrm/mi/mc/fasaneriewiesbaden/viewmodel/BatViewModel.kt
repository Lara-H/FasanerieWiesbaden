package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import java.util.Timer
import java.util.UUID
import kotlin.concurrent.schedule

class BatViewModel(val doorWidth: Dp): ViewModel() {
    val drillImgAltText = "Drill"

    val paddingBorder = 10.dp
    val drillWidth = doorWidth/2 - paddingBorder
    var isDone = mutableStateOf(false)
        private set
    var visible = mutableStateOf(true)
        private set
    var onUpdate = mutableStateOf(0)
        private set

    var drills = mutableStateListOf(
        Drill(rotation = 0f, offsetX = doorWidth-drillWidth-paddingBorder, offsetY = paddingBorder, imgPath = R.drawable.drill_1, isRotateable = false),
        Drill(rotation = (10..350).random().toFloat(), offsetX = paddingBorder, offsetY = paddingBorder, imgPath = R.drawable.drill_2),
        Drill(rotation = (10..350).random().toFloat(), offsetX = paddingBorder, offsetY = drillWidth+paddingBorder, imgPath = R.drawable.drill_3),
        Drill(rotation = (10..350).random().toFloat(), offsetX = paddingBorder, offsetY = drillWidth*2+paddingBorder, imgPath = R.drawable.drill_4),
        Drill(rotation = (10..350).random().toFloat(), offsetX = doorWidth-drillWidth-paddingBorder, offsetY = drillWidth*2+paddingBorder, imgPath = R.drawable.drill_5),
        )
        private set

    fun rotate(id: UUID, rotationChange: Float) {
        if (visible.value) {
            drills.forEach {
                if ((it.id == id) && it.isRotateable) {
                    it.rotation += rotationChange
                    checkIfDone()
                    updateUI()
                }
            }
        }
    }

    private fun checkIfDone() {
        var allDone = true
        drills.forEach {
            if (it.rotation > 5 && it.rotation < 355) {
                allDone = false
            }
        }
        if (allDone) {
            visible.value = false
            updateUI()
            Timer().schedule(3000) {
                isDone.value = true
            }
        }
    }

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    inner class Drill(val id: UUID = UUID.randomUUID(), var rotation: Float, val offsetX: Dp, val offsetY: Dp, val size: Dp = drillWidth, val imgPath: Int, val isRotateable: Boolean = true)
}