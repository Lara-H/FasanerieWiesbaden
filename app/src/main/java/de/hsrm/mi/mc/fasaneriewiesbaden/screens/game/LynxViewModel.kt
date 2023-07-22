package de.hsrm.mi.mc.fasaneriewiesbaden.screens.game

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import de.hsrm.mi.mc.fasaneriewiesbaden.R
import java.util.UUID

class LynxViewModel: ViewModel() {
    val itemImgAltText = "Item"
    val imgLst = listOf(R.drawable.lynx_placeholder, R.drawable.lynx_1, R.drawable.lynx_2, R.drawable.lynx_3, R.drawable.lynx_4, R.drawable.lynx_5, R.drawable.lynx_6)

    var isDone = mutableStateOf(false)
        private set
    var onUpdate = mutableStateOf(0)
        private set
    var items = mutableStateListOf(
        Item(imgLstKey = 0, imgCube = R.drawable.lynx_cube_1),
        Item(imgLstKey = 0, imgCube = R.drawable.lynx_cube_2),
        Item(imgLstKey = 0, imgCube = R.drawable.lynx_cube_3),
        Item(imgLstKey = 0, imgCube = R.drawable.lynx_cube_4),
        Item(imgLstKey = 0, imgCube = R.drawable.lynx_cube_5),
        Item(imgLstKey = 0, imgCube = R.drawable.lynx_cube_6),
        )
        private set

    fun handleClick(item: Item) {
        items.forEach {
            if (it.id == item.id) {
                if (it.imgLstKey < imgLst.size-1) {
                    it.imgLstKey += 1
                    it.imgPath = imgLst[it.imgLstKey]
                } else {
                    it.imgLstKey = 1
                    it.imgPath = imgLst[it.imgLstKey]
                }
            }
        }
        checkIfCorrect()
        updateUI()
    }

    private fun checkIfCorrect() {
        var allRight = true
        var i = 1
        items.forEach {
            if (it.imgLstKey != i) {
                allRight = false
            }
            i++
        }
        if (allRight) {
            isDone.value = true
        }
    }

    private fun updateUI() {
        onUpdate.value = (0..1_000_000).random()
    }

    inner class Item(val id: UUID = UUID.randomUUID(), var imgLstKey: Int, val imgCube: Int) {
        var imgPath = imgLst[imgLstKey]
    }
}