package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.ShopItem

class ShopViewModel : ViewModel() {
    private val _fruitList = MutableLiveData<List<ShopItem>>()
    val fruitList: LiveData<List<ShopItem>> get() = _fruitList

    init {
        loadFruits()
    }

    private fun loadFruits() {
        _fruitList.value = listOf(
            ShopItem(1, "Grapes", R.drawable.grape, 50),
            ShopItem(2, "Peach", R.drawable.apple, 40),
            ShopItem(3, "New Fruit", R.drawable.apple, 60),
        )
    }
}