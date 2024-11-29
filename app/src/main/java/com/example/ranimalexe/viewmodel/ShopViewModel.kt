package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.R
import com.example.ranimalexe.model.FoodItem
import com.example.ranimalexe.storage.UserData

class ShopViewModel : ViewModel() {
    private val _fruitList = MutableLiveData<List<FoodItem>>()
    val fruitList: LiveData<List<FoodItem>> get() = _fruitList

    init {
        loadFruits()
    }

    private fun loadFruits() {
        _fruitList.value = UserData.foodItems
    }
}