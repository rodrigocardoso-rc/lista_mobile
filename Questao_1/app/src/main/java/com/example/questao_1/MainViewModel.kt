package com.example.questao_1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var USER_NAME = "Rodrigo"
    private var USER_SURNAME = "Cardoso"

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>(USER_NAME)
    }
    val surname: MutableLiveData<String> by lazy {
        MutableLiveData<String>(USER_SURNAME)
    }
    val currImage: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(R.drawable.sr_1)
    }
    val amountLikes: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }
    val percentage: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }

    fun addLike() {
        amountLikes.value = (amountLikes.value ?: 0) + 1
        percentage.value = (amountLikes.value ?: 0) * 10

        updateCurrentImage()
    }

    private fun updateCurrentImage() {
        currImage.value = when (amountLikes.value) {
            in 0..3 -> R.drawable.sr_1
            in 4..9 -> R.drawable.sr_2
            in 9..11 -> R.drawable.sr_3
            else -> R.drawable.sr_3
        }
    }
}