package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.WardrobeItem

class ShopViewModel : ViewModel() {
    private val _fruitList = MutableLiveData<List<WardrobeItem>>()
    val fruitList: LiveData<List<WardrobeItem>> get() = _fruitList

    init {
        loadFruits()
    }

    private fun loadFruits() {
        _fruitList.value = listOf(
//            WardrobeItem(1, "Grapes", R.drawable.grape, 50),
//            WardrobeItem(
//                2, "Peach", R.drawable.apple, 40),
        )
    }
}