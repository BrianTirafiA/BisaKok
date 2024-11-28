package com.example.ranimalexe.model

data class FoodInventoryItem (
    val id: Int,
    val userId: Int,
    val foodId: Int,
    var amount: Int
)