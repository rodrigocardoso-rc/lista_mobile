package com.example.questao_2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.questao_2.model.GlassItem


class MainViewModel : ViewModel() {
    private val SIZE_GLASS: Int = 150;

    private var currentWeight = 0
    private val _weight = MutableLiveData(0)
    val weight: LiveData<Int> get() = _weight

    private val _amountRemain = MutableLiveData("0 copos ou 0L")
    val amountRemain: LiveData<String> get() = _amountRemain

    private val _amountDrunk = MutableLiveData("0L")
    val amountDrunk: LiveData<String> get() = _amountDrunk

    val listGlassItem = MutableLiveData<List<GlassItem>>(ArrayList())

    fun setWeight(value: Int) {
        _weight.value = value
    }

    fun onPressRestart() {
        _weight.value = currentWeight
        onPressCalculate()
    }

    fun onPressCalculate() {
        currentWeight = weight.value ?: 0

        val list = ArrayList<GlassItem>()
        val totalMl = _weight.value?.times(35)
        val totalGlass = totalMl?.div(SIZE_GLASS)

        for (i in 1..totalGlass!!) {
            list.add(GlassItem(SIZE_GLASS))
        }

        if ((SIZE_GLASS * list.size) < totalMl) {
            list.add(GlassItem(totalMl - (SIZE_GLASS * list.size)))
        }

        listGlassItem.value = list

        updateValues()
    }

    fun drinkWater(item: GlassItem) {
        item.toggleGlass()
        listGlassItem.value = listGlassItem.value

        updateValues()
    }

    private fun updateValues() {
        var drunkVol = 0.0
        var remainVol = 0.0

        listGlassItem.value?.forEach { item ->
            if (item.isEmpty.value == true) {
                drunkVol += item.volume
            } else {
                remainVol += item.volume
            }
        }

        _amountDrunk.value = "${(drunkVol / 1000)} L"
        _amountRemain.value = "${(remainVol / SIZE_GLASS).toInt()} copos ou ${remainVol / 1000}L"
    }
}