package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R

class GoatViewModel(): ViewModel() {
    val goatImgAltText = "Goat"

    val totalPoints = 5

    var isDone = mutableStateOf(false)
        private set
    var onUpdate = mutableStateOf(0)
        private set

    var items = mutableStateListOf(
        Item(imgPath = R.drawable.goat_1, isGoat = true),
        Item(imgPath = R.drawable.goat_2, isGoat = false),
        Item(imgPath = R.drawable.goat_3, isGoat = true),
        Item(imgPath = R.drawable.goat_4, isGoat = false),
        Item(imgPath = R.drawable.goat_5, isGoat = true)
    )
        private set
    var currentPoints by mutableStateOf(0)
        private set

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    fun addPoint() {
        currentPoints++
        if (currentPoints >= totalPoints) {
            isDone.value = true
        }
        updateUI()
    }

    inner class Item(val imgPath: Int, val isGoat: Boolean)

}