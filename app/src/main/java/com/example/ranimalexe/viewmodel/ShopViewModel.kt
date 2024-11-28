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
            ShopItem(1, "Burger", R.drawable.borgir, 10),
            ShopItem(2, "Sausage", R.drawable.sausage, 20),
            ShopItem(3, "Skewer", R.drawable.skewer, 30),
            ShopItem(4, "Chicken", R.drawable.chickenstick, 40),
            ShopItem(5, "Steak", R.drawable.steak, 50),
        )
    }
}