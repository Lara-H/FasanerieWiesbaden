package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import java.util.Timer
import java.util.UUID
import kotlin.concurrent.schedule

class RaccoonViewModel: ViewModel() {
    private val emptyItem = Item(imgPath = 0)
    private val colorUndecided = Color.Gray
    private val colorWrong = Color.Red
    private val colorCorrect = Color.Green

    var isDone = mutableStateOf(false)
        private set
    var onUpdate = mutableStateOf(0)
        private set
    var items = mutableStateListOf(
        Item(imgPath = R.drawable.raccoon_sock_5),
        Item(imgPath = R.drawable.raccoon_sock_6),
        Item(imgPath = R.drawable.raccoon_sock_2),
        Item(imgPath = R.drawable.raccoon_sock_1),
        Item(imgPath = R.drawable.raccoon_sock_4),
        Item(imgPath = R.drawable.raccoon_sock_3),
        Item(imgPath = R.drawable.raccoon_sock_4),
        Item(imgPath = R.drawable.raccoon_sock_6),
        Item(imgPath = R.drawable.raccoon_sock_2),
        Item(imgPath = R.drawable.raccoon_sock_5),
        Item(imgPath = R.drawable.raccoon_sock_3),
        Item(imgPath = R.drawable.raccoon_sock_1),
        )
        private set
    var selectedFirst = mutableStateOf(emptyItem)
        private set
    var selectedSecond = mutableStateOf(emptyItem)
        private set
    var selectedColor = mutableStateOf(colorUndecided)
        private set

    fun handleClick(item: Item) {
        if (selectedFirst.value == emptyItem) {
            selectedFirst = mutableStateOf(item)
            updateUI()
        } else {
            selectedSecond = mutableStateOf(item)
            selectedColor =
                if (selectedFirst.value.imgPath == item.imgPath) {
                    mutableStateOf(colorCorrect)
                } else {
                    mutableStateOf(colorWrong)
                }
            if (selectedColor.value == colorCorrect) {
                foundPair(item.imgPath)
            }
            selectedFirst = mutableStateOf(emptyItem)
            selectedSecond = mutableStateOf(emptyItem)
            selectedColor = mutableStateOf(colorUndecided)
            checkIfAllFound()
            updateUI()
        }
    }

    private fun foundPair(imgPath: Int) {
        val newItems = mutableStateListOf<Item>()
        items.forEach {
            if (it.imgPath != imgPath) {
                newItems.add(it)
            } else {
                val newItem = Item(it.id, it.imgPath, isFound = true)
                newItems.add(newItem)
            }
        }
        items = newItems
    }

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    private fun checkIfAllFound() {
        var allFound = true
        items.forEach {
            if (!it.isFound) {
                allFound = false
            }
        }
        if (allFound) {
            isDone.value = true
        }
    }

    inner class Item(val id: UUID = UUID.randomUUID(), val imgPath: Int, val isFound: Boolean = false)
}