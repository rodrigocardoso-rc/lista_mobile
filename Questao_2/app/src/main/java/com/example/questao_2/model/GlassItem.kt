package com.example.questao_2.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.questao_2.R

class GlassItem(var volume: Int) {
    private val _isEmpty = MutableLiveData(false)
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _image = MutableLiveData(R.drawable.full)
    val image: LiveData<Int> get() = _image

    fun getVolumeStr(): String {
        return "$volume ml"
    }

    fun toggleGlass() {
        if (_isEmpty.value == true) {
            fill()
        } else {
            drink()
        }
    }

    private fun fill() {
        _isEmpty.value = false
        _image.value = R.drawable.full
    }

    private fun drink() {
        _isEmpty.value = true
        _image.value = R.drawable.empty
    }
}