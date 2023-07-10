package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import java.util.UUID

class BatViewModel(val screenWidth: Dp): ViewModel() {
    var onUpdate = mutableStateOf(0)
        private set
    // TODO: Größen abhängig von Screengröße machen, nicht fest
    var drills = mutableStateListOf(
        Drill(rotation = 0f, offsetX = 143.dp, offsetY = (-350).dp, size = 150.dp, imgPath = R.drawable.drill_1, isRotateable = false),
        Drill(rotation = 123f, offsetX = 0.dp, offsetY = (-250).dp, size = 180.dp, imgPath = R.drawable.drill_2),
        Drill(rotation = 321f, offsetX = 140.dp, offsetY = (-125).dp, size = 180.dp, imgPath = R.drawable.drill_3),
        Drill(rotation = 147f, offsetX = 0.dp, offsetY = 0.dp, size = 180.dp, imgPath = R.drawable.drill_4),
        )
        private set

    fun rotate(id: UUID, rotationChange: Float) {
        drills.forEach() {
            if ((it.id == id) && it.isRotateable) {
                it.rotation += rotationChange
                updateUI()
            }
        }
    }

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    inner class Drill(val id: UUID = UUID.randomUUID(), var rotation: Float, val offsetX: Dp, val offsetY: Dp, val size: Dp, val imgPath: Int, val isRotateable: Boolean = true)
}