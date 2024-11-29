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
//        _fruitList.value = listOf(
//            FoodItem(1, "Burger", R.drawable.borgir, 10),
//            FoodItem(2, "Sausage", R.drawable.sausage, 20),
//            FoodItem(3, "Skewer", R.drawable.skewer, 30),
//            FoodItem(4, "Chicken", R.drawable.chickenstick, 40),
//            FoodItem(5, "Steak", R.drawable.steak, 50),
//        )
        _fruitList.value = UserData.foodItems
    }
}