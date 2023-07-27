package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R

class BearViewModel(): ViewModel() {
    var onUpdate = mutableStateOf(0)
        private set
    var currentPoints by mutableStateOf(0)
        private set
    var currentRotation by mutableStateOf(30f)
        private set
    var drops = mutableStateListOf(
        Drop(),
        Drop(),
        Drop(),
        Drop(),
        Drop(),
        )
        private set

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    fun rotate() {
        currentRotation *= (-1)
    }

    fun drop() {
        var stop = false
        drops.forEach {
            if (!it.isDropped && !stop) {
                it.isDropped = true
                stop = true
            }
        }
    }

    fun addPoint() {
        currentPoints++
    }

    inner class Drop(val imgPath: Int = R.drawable.beehoney, var isDropped: Boolean = false)

}