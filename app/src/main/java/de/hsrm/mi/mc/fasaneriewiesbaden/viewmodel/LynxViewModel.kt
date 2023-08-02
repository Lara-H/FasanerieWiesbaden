package de.hsrm.mi.mc.fasaneriewiesbaden.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import java.util.UUID

class LynxViewModel: ViewModel() {
    val imgLst = listOf(R.drawable.lynx_5, R.drawable.lynx_2, R.drawable.lynx_6, R.drawable.lynx_1, R.drawable.lynx_4, R.drawable.lynx_3)

    var isDone = mutableStateOf(false)
        private set
    var onUpdate = mutableStateOf(0)
        private set
    var items = mutableStateListOf(
        Item(correctImgPath = R.drawable.lynx_1, cubeImgPath = R.drawable.lynx_cube_1),
        Item(correctImgPath = R.drawable.lynx_2, cubeImgPath = R.drawable.lynx_cube_2),
        Item(correctImgPath = R.drawable.lynx_3, cubeImgPath = R.drawable.lynx_cube_3),
        Item(correctImgPath = R.drawable.lynx_4, cubeImgPath = R.drawable.lynx_cube_4),
        Item(correctImgPath = R.drawable.lynx_5, cubeImgPath = R.drawable.lynx_cube_5),
        Item(correctImgPath = R.drawable.lynx_6, cubeImgPath = R.drawable.lynx_cube_6),
        )
        private set

    fun handleClick(item: Item) {
        items.forEach {
            if (it.id == item.id) {
                switchToNextCard(it)
            }
        }
        checkIfCorrect()
        updateUI()
    }

    private fun switchToNextCard(it: Item) {
        if (it.currentImgLstKey < imgLst.size-1) {
            it.currentImgLstKey += 1
            it.currentImgPath = imgLst[it.currentImgLstKey]
        } else {
            it.currentImgLstKey = 0
            it.currentImgPath = imgLst[0]
        }
    }

    private fun checkIfCorrect() {
        var allRight = true
        items.forEach {
            if (it.currentImgPath != it.correctImgPath) {
                allRight = false
            }
        }
        if (allRight) {
            isDone.value = true
        }
    }

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    inner class Item(val id: UUID = UUID.randomUUID(), val correctImgPath: Int, var currentImgLstKey: Int = (imgLst.indices).random(), val cubeImgPath: Int) {
        var currentImgPath = imgLst[currentImgLstKey]
    }
}