package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel(): ViewModel() {

    var topBarTitle = mutableStateOf("")
        private set

    var isExpanded = mutableStateOf(false)
        private set

    var onUpdate = mutableStateOf(0)
        private set

    fun updateTitle(newTitle: String) {
        topBarTitle = mutableStateOf(newTitle)
        updateUI()
    }

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