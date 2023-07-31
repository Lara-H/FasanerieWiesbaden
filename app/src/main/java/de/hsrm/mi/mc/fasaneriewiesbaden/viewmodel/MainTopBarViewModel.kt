package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R

class MainTopBarViewModel(): ViewModel() {
    var isExpanded = mutableStateOf(false)
        private set

    var onUpdate = mutableStateOf(0)
        private set

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    fun changeExpanded() {
        isExpanded = mutableStateOf(!isExpanded.value)
        updateUI()
    }

    fun changeLanguage() {
        updateUI()
    }

    fun resetGame() {
        updateUI()
    }

}