package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.ItemType
import com.example.ranimalexe.model.ShopItem

class ShopViewModel : ViewModel() {
    private val _fruitList = MutableLiveData<List<ShopItem>>()
    val fruitList: LiveData<List<ShopItem>> get() = _fruitList

    init {
        loadFruits()
    }

    private fun loadFruits() {
        _fruitList.value = listOf(
            ShopItem("1", "1", 1, ItemType.FoodItem, "Grape", R.drawable.grape),
            ShopItem("2", "2", 2, ItemType.FoodItem, "Peach", R.drawable.apple),
            ShopItem("3", "3", 3, ItemType.FoodItem, "Peach", R.drawable.apple),
        )
    }
}